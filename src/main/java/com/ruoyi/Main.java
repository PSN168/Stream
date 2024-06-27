package com.ruoyi;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.nio.file.Path;


@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

        String data = "https://www.google.com/";
        String path = "/Users/root/Desktop/qrcode.jpg";

        try{
            BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE,500,500);
            MatrixToImageWriter.writeToPath(bitMatrix,"jpg", Path.of(path));
        }catch (WriterException | IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println("QR code has been generated!");
    }
}