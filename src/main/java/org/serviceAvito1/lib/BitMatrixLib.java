package org.serviceAvito1.lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
//c'est un class Api qui server a genere des Qrcode pour les annonces 
public class BitMatrixLib {
	 public   BitMatrix generateMatrix(String data, int size) throws WriterException {
	        BitMatrix bitMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, size, size);
	        return bitMatrix;
	    }
	 public   void writeImage(String outputFileName, String imageFormat, BitMatrix bitMatrix) throws IOException  {
	        FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFileName));
	        MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, fileOutputStream);
	        fileOutputStream.close();
	    }
}
