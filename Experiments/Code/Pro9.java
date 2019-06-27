import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import com.google.zxing.*;
import com.google.zxing.client.j2se.*;
import com.google.zxing.common.*;
import com.google.zxing.qrcode.decoder.*;

class CreateQRCode {
	static int width = 500;
	static int height = 500; 
	public static void run(String content) {
		String format="png";
		HashMap hints=new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M); 
		hints.put(EncodeHintType.MARGIN, 2);
		try {

			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			Path file = new File("code.png").toPath();
			MatrixToImageWriter.writeToPath(bitMatrix, format, file);
		} catch (Exception e) {
			System.out.println("create is Error:" + e.getMessage());
		}
	}
}
class ReadQRCode {
	public void run(String fileName){
		MultiFormatReader formatReader=new MultiFormatReader();
		File file=new File(fileName);
		BufferedImage image;
		try {
			image = ImageIO.read(file);
			BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
			Result result=formatReader.decode(binaryBitmap);
			
			System.out.println("解析结果："+result.toString());
			System.out.println("二维码格式："+result.getBarcodeFormat());
			System.out.println("二维码文本内容："+result.getText());
        }catch (IOException e) {
        	System.out.println("read IOException is Error:" + e.getMessage());
        }catch (NotFoundException e) {
        	System.out.println("read NotFoundException is Error:" + e.getMessage());
        }
    }
}

public class Pro9{
	public static void main(String[] argv) {
		Scanner cin = new Scanner(System.in);
		CreateQRCode cORCode = new CreateQRCode();
		ReadQRCode rQRCode = new ReadQRCode();
		String fn;
		while(true) {
			fn = cin.next();
			cORCode.run(fn);
			rQRCode.run("code.png");
		}
	}
}