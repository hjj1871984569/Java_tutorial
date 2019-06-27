import java.io.*;
import java.net.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
class WebCrawler{
	PrintWriter cout;
	String newsWeb = "news.sina.com.cn";
	public void run() {
		try{
			cout = new PrintWriter(new FileWriter(new File("web_news.txt")));
		}catch(FileNotFoundException e) {
			System.out.println("run FileNotFoundException :" + e.getMessage());
		}catch(IOException e) {
			System.out.println("run IOException :" + e.getMessage());
		}
		getInetAddressInfo();
		String htmlData = getHtmlData();
		dealHtmlData(htmlData);
		cout.close();
	}
	private void getInetAddressInfo() {
		InetAddress inetAddress;
		try{
			inetAddress = InetAddress.getByName(newsWeb);
			cout.println("newsWeb InetAddress : ");
			cout.println("HostName : "+inetAddress.getHostName());
			cout.println("Address : "+inetAddress.getHostAddress());
			cout.println();
			inetAddress = InetAddress.getLocalHost();
			cout.println("Local InetAddress : ");
			cout.println("HostName : "+inetAddress.getHostName());
			cout.println("Address : "+inetAddress.getHostAddress());
			cout.println();
			cout.flush();
		}catch (UnknownHostException e) {
			System.out.println("run UnknownHostException:" + e.getMessage());
		}
	}
	private String getHtmlData() {
		StringBuffer htmlData = new StringBuffer();
		try{
			cout.println("正在爬取 "+ newsWeb);
			URL web = new URL("https://" + newsWeb );
			BufferedReader cin = new BufferedReader(new InputStreamReader(web.openStream(),"utf-8"));
            String data; 
			while((data = cin.readLine()) != null) 
            	htmlData.append(data + "\n");
			cin.close();
		} catch (MalformedURLException e){
			System.out.println("run MalformedURLException:" + e.getMessage());
		} catch (IOException e){
			System.out.println("run IOException:" + e.getMessage());
		}
		return htmlData.toString();
    }
	private void dealHtmlData(String htmlData) {
		cout.println("********************");
		Document doc = Jsoup.parse(htmlData);
		Element title = doc.getElementsByTag("head").first().getElementsByTag("title").first();
		
		cout.println("标题 : "+title.html());
		
		Element block;
		block = doc.getElementById("blk_yw_01");
		block = block.getElementById("syncad_1");
		cout.println("要闻 : ");
		for (Element item : block.getElementsByTag("h1"))
			cout.println("  - "+item.getElementsByTag("a").first().html());
		
		block = doc.getElementById("Blk01_Focus_Cont");
		cout.println("焦点图 : ");
		for (Element item : block.getElementsByTag("div"))
			cout.println("  - "+item.getElementsByTag("a").first().html());
		
		cout.println("国内新闻 : ");
		block = doc.getElementById("blk_new_gnxw");
		for (Element item : block.getElementsByTag("li"))
			cout.println("  - "+item.getElementsByTag("a").first().html());
		
		cout.println("国际新闻 : ");
		block = doc.getElementById("blk_gjxw_01");
		for (Element item : block.getElementsByTag("li"))
			cout.println("  - "+item.getElementsByTag("a").first().html());
		cout.flush();
    }
}

public class Pro8{
	public static void main(String[] args){
		WebCrawler crawler = new WebCrawler();
		crawler.run();
	}
}
