package com.phantommentalists;

public class TurretPixyPacket {
	public int signature;
	public int X;
	public int Y;
	public int width;
	public int height;
	
	//public int checksumError;
	
	public String toString() {
		return "" +
	" S:" + signature +
	" X:" + X + 
	" Y:" + Y +
	" W:" + width + 
	" H:" + height;
	}
}