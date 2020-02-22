/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurretPixy {
    String name;
    TurretPixyPacket values;
    I2C pixy;
    Port port = Port.kOnboard;
    TurretPixyPacket[] packets;
    TurretPixyException pExc;
    String print;

    public TurretPixy(String id, I2C argPixy, TurretPixyPacket[] argPixyPacket, TurretPixyException argPixyException,
            TurretPixyPacket argValues) {
        pixy = argPixy;
        packets = argPixyPacket;
        pExc = argPixyException;
        values = argValues;
        name = "Pixy_" + id;
    }

    // This method parses raw data from the pixy into readable integers
    public int cvt(byte upper, byte lower) {
        return (((int) upper & 0xff) << 8) | ((int) lower & 0xff);
    }

    // This method gathers data, then parses that data, and assigns the ints to
    // global variables
    // The
    // signature
    // should
    // be
    // which
    // number
    // object
    // in
    // pixymon you are trying to get data for
    public TurretPixyPacket readPacket(int signature) throws TurretPixyException {
        int checkSum;
        int sig;
        byte[] rawData = new byte[32];
        // SmartDashboard.putString("rawData", rawData[0] + " " + rawData[1] + "
        // " + rawData[15] + " " + rawData[31]);
        try {
            pixy.readOnly(rawData, 32);
        } catch (RuntimeException e) {
            SmartDashboard.putString(name + "Status", e.toString());
            System.out.println(name + "  " + e);
        }
        if (rawData.length < 32) {
            SmartDashboard.putString(name + "Status", "raw data length " + rawData.length);
            System.out.println("byte array length is broken length=" + rawData.length);
            return null;
        }
        for (int i = 0; i <= 16; i++) {
            int syncWord = cvt(rawData[i + 1], rawData[i + 0]); // Parse first 2
                                                                // bytes
            if (syncWord == 0xaa55) { // Check is first 2 bytes equal a "sync
                                      // word", which indicates the start of a
                                      // packet of valid data
                syncWord = cvt(rawData[i + 3], rawData[i + 2]); // Parse the
                                                                // next 2 bytes
                if (syncWord != 0xaa55) { // Shifts everything in the case that
                                          // one syncword is sent
                    i -= 2;
                }
                // This next block parses the rest of the data
                checkSum = cvt(rawData[i + 5], rawData[i + 4]);
                sig = cvt(rawData[i + 7], rawData[i + 6]);
                if (sig <= 0 || sig > packets.length) {
                    break;
                }

                packets[sig - 1] = new TurretPixyPacket();
                packets[sig - 1].X = cvt(rawData[i + 9], rawData[i + 8]);
                packets[sig - 1].Y = cvt(rawData[i + 11], rawData[i + 10]);
                packets[sig - 1].width = cvt(rawData[i + 13], rawData[i + 12]);
                packets[sig - 1].height = cvt(rawData[i + 15], rawData[i + 14]);
                // Checks whether the data is valid using the checksum *This if
                // block should never be entered*
                if (checkSum != sig + packets[sig - 1].X + packets[sig - 1].Y + packets[sig - 1].width
                        + packets[sig - 1].height) {
                    packets[sig - 1] = null;
                    throw pExc;
                }
                break;
            } else
                SmartDashboard.putNumber("syncword: ", syncWord);
        }
        // Assigns our packet to a temp packet, then deletes data so that we
        // dont return old data
        TurretPixyPacket pkt = packets[signature - 1];
        packets[signature - 1] = null;
        return pkt;
    }

    private byte[] readData(int len) {
        byte[] rawData = new byte[len];
        try {
            pixy.readOnly(rawData, len);
        } catch (RuntimeException e) {
            SmartDashboard.putString(name + "Status", e.toString());
            System.out.println(name + "  " + e);
        }
        if (rawData.length < len) {
            SmartDashboard.putString(name + "Status", "raw data length " + rawData.length);
            System.out.println("byte array length is broken length=" + rawData.length);
            return null;
        }
        return rawData;
    }

    private int readWord() {
        byte[] data = readData(2);
        if (data == null) {
            return 0;
        }
        return cvt(data[1], data[0]);
    }

    private TurretPixyPacket readBlock(int checksum) {
        // See Object block format section in
        // http://www.cmucam.org/projects/cmucam5/wiki/Porting_Guide#Object-block-format
        // Each block is 14 bytes, but we already read 2 bytes for checksum in
        // caller so now we need to read rest.

        byte[] data = readData(12);
        if (data == null) {
            return null;
        }
        TurretPixyPacket block = new TurretPixyPacket();
        block.signature = cvt(data[1], data[0]);
        if (block.signature <= 0 || block.signature > 7) {
            return null;
        }
        block.X = cvt(data[3], data[2]);
        block.Y = cvt(data[5], data[4]);
        block.width = cvt(data[7], data[6]);
        block.height = cvt(data[9], data[8]);

        int sum = block.signature + block.X + block.Y + block.width + block.height;
        if (sum != checksum) {
            return null;
        }
        return block;
    }

    private final int MAX_SIGNATURES = 7;
    private final int OBJECT_SIZE = 14;
    private final int START_WORD = 0xaa55;
    private final int START_WORD_CC = 0xaa5;
    private final int START_WORD_X = 0x55aa;

    public boolean getStart() {
        int numBytesRead = 0;
        int lastWord = 0xffff;
        // This while condition was originally true.. may not be a good idea if
        // something goes wrong robot will be stuck in this loop forever. So
        // let's read some number of bytes and give up so other stuff on robot
        // can have a chance to run. What number of bytes to choose? Maybe size
        // of a block * max number of signatures that can be detected? Or how
        // about size of block and max number of blocks we are looking for?
        while (numBytesRead < (OBJECT_SIZE * MAX_SIGNATURES)) {
            int word = readWord();
            numBytesRead += 2;
            if (word == 0 && lastWord == 0) {
                return false;
            } else if (word == START_WORD && lastWord == START_WORD) {
                return true;
            } else if (word == START_WORD_CC && lastWord == START_WORD) {
                return true;
            } else if (word == START_WORD_X) {
                byte[] data = readData(1);
                numBytesRead += 1;
            }
            lastWord = word;
        }
        return false;
    }

    private boolean skipStart = false;

    public TurretPixyPacket[] readBlocks() {
        // This has to match the max block setting in pixymon?
        int maxBlocks = 2;
        TurretPixyPacket[] blocks = new TurretPixyPacket[maxBlocks];

        if (!skipStart) {
            if (getStart() == false) {
                return null;
            }
        } else {
            skipStart = false;
        }
        for (int i = 0; i < maxBlocks; i++) {
            // Should we set to empty PixyPacket? To avoid having to check for
            // null in callers?
            blocks[i] = null;
            int checksum = readWord();
            if (checksum == START_WORD) {
                // we've reached the beginning of the next frame
                skipStart = true;
                return blocks;
            } else if (checksum == START_WORD_CC) {
                // we've reached the beginning of the next frame
                skipStart = true;
                return blocks;
            } else if (checksum == 0) {
                return blocks;
            }
            blocks[i] = readBlock(checksum);
        }
        return blocks;
    }
}
