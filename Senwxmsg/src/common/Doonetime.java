package common;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import cn.hutool.core.swing.clipboard.ImageSelection;

public class Doonetime {
	

    //后台接口
    private final static String  apiurl="https://hlapi.top/cjlq";
	
    public static void main(String[] args) {
    	String wxq="文件传输助手";
    	String gjc="女装";
    	String userid="1774";
    	String  gvtj=null;
    	
	}
	
    //针对考察用户发送
    public static void   senwx(String wxq,String gjc,String  userid,String gltj, String start_price, String end_price) throws InterruptedException {
    	 
    	String res="任务执行失败";
    	String  goodclass=gjc;
    	String friendNickNamelist = wxq;
		String[] friendNickNamelist2 = friendNickNamelist.split("-");
		WxUtil  u=new  WxUtil();
		File basefile = new File("C:\\lftx\\imgtemp");   //path为给定的文件夹地址
		 u.remove(basefile);
		
		
       	for (int i = 0; i < friendNickNamelist2.length; i++) {
				if(friendNickNamelist2[i] !=null) {
					
	  					String friendNickName=friendNickNamelist2[i];
						searchMyFriendAndSend(friendNickName);
						 
						  //String im=gethaibao(goodclass,userid);
						  List <String> haibaolist=gethaibao(goodclass,userid,gltj,start_price,end_price);
						  if(haibaolist.size()>0) {
							  for (int j = 0; j < haibaolist.size(); j++) {
								  String im=haibaolist.get(j);
								  System.out.println("海报地址："+im);
								  if(im !=null) {
					  					//sendOneMsg("@所有人");
									  Thread.sleep(15000);
					  					sendOneimgMsg(im);
					  					res="本次任务执行成功";
					  				}
							}
						  }

						  
		  				
		  				
	  				
				}else {
					System.out.println("错误好友名："+friendNickNamelist);
				}
			}
       
    }
   
	   public static void sendOneimgMsg(String imgPath) {
	        // 创建Robot对象
	        Robot robot = getRobot();
	        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
	        
	        Toolkit kit=Toolkit.getDefaultToolkit();
	        // 将字符串复制到剪切板
	        Transferable tText = new ImageSelection(kit.getImage(imgPath));
	        clip.setContents(tText, null);
	        // 以下两行按下了ctrl+v，完成粘贴功能
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        // 回车发送
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.delay(1000);
	    }
	    public static Robot getRobot(){
	        // 创建Robot对象
	        Robot robot = null;
	        try {
	            robot = new Robot();
	        } catch (AWTException e) {
	            e.printStackTrace();
	        }
	        return robot;
	    }
	    public static void searchMyFriendAndSend(String friendNickName) throws InterruptedException {
	        // 创建Robot对象
	        Robot robot = getRobot();


	        robot.delay(5000);
	        
	        //打开微信 Ctrl+Alt+W
	        assert robot != null;

	        System.out.println("切换屏幕");
		     // 回到桌面界面
	        System.out.println("本次完成回到桌面");
	        robot.keyPress(KeyEvent.VK_WINDOWS);
	        robot.keyPress(KeyEvent.VK_D);

	        robot.keyRelease(KeyEvent.VK_D);
	        robot.keyRelease(KeyEvent.VK_WINDOWS);
	        robot.delay(3000);
	        
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_ALT);
	        robot.keyPress(KeyEvent.VK_W);
	        //释放Ctrl按键，像Ctrl，退格键，删除键这样的功能性按键，在按下后一定要释放
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyRelease(KeyEvent.VK_ALT);

	        // 该延迟不能少，否则无法搜索
	        robot.delay(5000);

	        // Ctrl + F 搜索指定好友
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_F);
	        robot.keyRelease(KeyEvent.VK_CONTROL);

	        // 将好友昵称发送到剪切板
	        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
	        Transferable tText = new StringSelection(friendNickName);
	        clip.setContents(tText, null);
	        // 以下两行按下了ctrl+v，完成粘贴功能
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.delay(1000);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.delay(3000);

	    }
	    
	    public static  List<String>  gethaibao(String goodclass,String userid,String gltj, String start_price, String end_price ) {
	    	List<String> haibaop= new ArrayList<>();
	    	try {
	        	
	        	String coupon="0";
	        	String title="";
	        	JSONObject  getdata=null;
	        	int p=(int)(Math.random() * 10000);
            	String p2=Integer.toString(p);               
                System.out.println("查询页码："+p2);
	        	JSONObject  jsonObject=getgoods(p2,"5",goodclass,start_price,end_price);
                JSONArray  jsonObject2=jsonObject.getJSONArray("data");
               int  goodsnum= jsonObject2.size();
               System.out.println("查询到商品数量"+goodsnum);
               
               
               if(goodsnum>0) {
               	for (int i = 0; i < goodsnum; i++) {
               		
               		 getdata= jsonObject2.getJSONObject(i);
   	                 System.out.println("getdata:"+getdata);
   	                 title=getdata.get("title").toString();
   	                 coupon= getdata.get("coupon").toString();	   	            	
	   	            	System.out.println("优惠券金额："+coupon);
	   	            	if(coupon !="0" && Integer.parseInt(coupon)>=5 && title !="" && title!=gltj) {
	   		               	 String pict_url=getdata.get("pict_url").toString();
	   		               	 System.out.println("获取到主图："+pict_url);
	   		               	
	   		               	 System.out.println("获取到标题："+title);
	   		               	 String zk_final_price=getdata.get("zk_final_price").toString();
	   		               	 System.out.println("获取到原价："+zk_final_price);
	   		               	Double priceint=Double.parseDouble(zk_final_price)-Double.parseDouble(coupon);
	   		               	
	   		               	String price=new DecimalFormat("#.00").format(priceint);
	   		               	// String price=String.valueOf(priceint);
	   		               	 System.out.println("获取到券后价："+price);
	   		               	 String   goodsId= getdata.get("num_iid").toString();
	   		               	 System.out.println("获取到商品id："+goodsId);
	   		               	 System.out.println("组装获取小程序二维码数据");
	   		               	 String scene=goodsId;
	   		               	 System.out.println("获取小程序码入参："+scene);              	 
	   		               	 String codep=generateQrCode2("pages/one", scene,userid);        	 
	   		               	 System.out.println("获取到小程序码："+codep);
	   		               	 if(codep !=null) {             		
	   		    				String haibaoi=haibao(pict_url, title, price, zk_final_price, coupon, codep);
	   		    				System.out.println("本次海报地址："+haibaoi);
	   		    				haibaop.add(haibaoi);
            		               		 
	   		               	 }              	                	 
	   		            	}else {
	   		            		System.out.println("优惠券金额不符合要求");
	   		            		
	   		            	}
					}
               }else {
               	 System.out.println("商品查询结果为空");
               }
	        	  
			} catch (Exception e) {
				// TODO: handle exception
				
				System.out.println(e);
				
			}
	    	
			return haibaop; 
	    	
	    }
	    
	    //生成小程序码
	    
	    public static  String generateQrCode2(String page, String scene ,String userid) throws IOException  {
	    	String  filePath=null;
	    	
	        
	        	System.out.println("生成页面参数："+scene);
	        	WxUtil u=new  WxUtil();
	        	HttpUtil hkon=new  HttpUtil();
	        	String tokenstr=hkon.doGet(apiurl+"/xcxapi/getAccessToken?aid=1");
	        	System.out.println("请求token返回："+tokenstr);
	        	JSONObject jsonObjecttoken = JSONObject.parseObject(tokenstr);
	        	
	        	String accessToken=jsonObjecttoken.get("access_token").toString();
	        	System.out.println("accessToken:"+accessToken);
	            //u.delFileByName("C:\\nginx-1.13.8\\html\\dist\\static\\ewm", str2);
	            
	            Date date = new Date();//获取当前的日期
	            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
	            String str = df.format(date);//获取String类型的时间
	            //调用微信接口生成二维码

	            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken);
	            
	            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
	         
	            httpURLConnection.setRequestMethod("POST");// 提交模式

	            // conn.setConnectTimeout(10000);//连接超时 单位毫秒

	            // conn.setReadTimeout(2000);//读取超时 单位毫秒

	            // 发送POST请求必须设置如下两行

	            httpURLConnection.setDoOutput(true);

	            httpURLConnection.setDoInput(true);

	            // 获取URLConnection对象对应的输出流

	            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
	           
	            // 发送请求参数

	            JSONObject paramJson = new JSONObject();
	            WxUtil  v=new WxUtil();
	            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	            System.out.println("生成md5参数======="+scene+"||"+timeStamp);
	            //这就是你二维码里携带的参数 String型  名称不可变,先进行MD5加密
	            scene=scene+"||"+timeStamp;
	           String  scene2 =v.md5(scene);
	           System.out.println("生成小程序二维码最终参数："+ scene2);
	           System.out.println("生成md5结果值======="+scene2);
	           HttpUtil hmd=new  HttpUtil();
	           scene = URLEncoder.encode(scene, "UTF-8");
	           String insetmd5url=apiurl+"/xcxapi/insertmd5str?aid=1&scene="+scene2+"&str="+scene;
	           
	           String mdres=hmd.doGet(insetmd5url);
	           mdres=mdres.replaceAll("\"", "");
	           System.out.println("写入数据库md信息结果："+mdres);

	           if(mdres.equals("成功")) {
		            paramJson.put("scene",scene2);

		            //注意该接口传入的是page而不是path

		            paramJson.put("page", page);

		            //这是设置扫描二维码后跳转的页面

		            paramJson.put("width", 200);

		            paramJson.put("is_hyaline", false);

		            paramJson.put("auto_color", true);
		            System.out.println("生成图片入参======="+paramJson.toString());
		          
		            printWriter.write(paramJson.toString());

		            // flush输出流的缓冲

		            printWriter.flush();
		            



		            //开始获取数据
		          String e= httpURLConnection.getResponseMessage();
		     
		          System.out.println(e);
		            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());

		            

		            filePath="C:\\lftx\\imgtemp\\"+str+".jpg";
		            OutputStream os = new FileOutputStream(new File(filePath));

		            int len;

		            byte[] arr = new byte[1024];

		            while ((len = bis.read(arr)) != -1) {
		            	
		            	

		                os.write(arr, 0, len);

		                os.flush();

		            }
		            Reader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
		            os.close();
		            in.close();
		            bis.close();
		            printWriter.close();
		        System.out.println("打开地址查看生成的二维码：" + filePath);
	           }else {
	        	   System.out.println("写入数据库md5信息失败");
	           }
	           

	       
	        return filePath;
	        

	    }
	    
	    public  static  JSONObject getgoods(String page_no,String page_size,String q, String start_price2, String end_price2) {
			System.out.println("查询商品222");
	    	String start_price=start_price2;
	    	String end_price=end_price2;
	    	System.out.println("价格区间："+start_price+"-"+end_price);
	    	String   params="apikey=yYQzHZw9Mx9Tff6xOcTLoky5d9Xe7urM&sort=tk_total_sales"+"&page_no="+page_no+"&page_size="+page_size+"&start_price="+start_price+"&end_price="+end_price+"&q="+q;
	    	
	    	HttpUtil  h  =new  HttpUtil();
	    	String url="http://api.tbk.dingdanxia.com/tbk/super_search";
	    	String res= h.doPost(url, params);
	    	
	    	 System.out.println("淘宝查询结果="+res);
	    	 JSONObject jsonObject = JSONObject.parseObject(res);
	    	return jsonObject;
	    }
	    
	    public static String   haibao(String   topimgpath ,String title,String price ,String oprice,String  coupon,String  ewmpath) {

	    	/*
	    	
	    	  topimgpath ="https://img.alicdn.com/bao/uploaded/i2/452325706/O1CN01lmu5OT1s1OjiFT6eZ_!!452325706.jpg";
	    	 
	    	  ewmpath="F:\\taobaokejavawork\\filetemp\\cs.jpg";
	    	 
	    	  title="Q女家 温莎学院/刺绣假两件衬衫撞色显瘦连衣裙秋冬黑色2022新款瘦连衣裙秋冬黑色2022新款";
	    	  price="2999.99";
	    	  oprice="3999";
	    	  coupon="1000";
	    	  
	    	*/
	   	 System.out.println("开始绘制海报==");
	   	System.out.println("topimgpath"+topimgpath);
	   	System.out.println("title"+title);
	   	System.out.println("price"+price);
	   	System.out.println("oprice"+oprice);
	   	System.out.println("coupon"+coupon);
	   	System.out.println("ewmpath"+ewmpath);
	    	
	    	 String  filePath=null;
	    	
			try {
				
		        InputStream is = null;
		        FileOutputStream os = null;
		        
	            Date date = new Date();//获取当前的日期
	            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
	            String str = df.format(date);//获取String类型的时间
	            CharacterUtils  strc=new CharacterUtils();
	            String ss=strc.getRandomString(16);
		     // 构造URL
	            URL url = new URL(topimgpath);
	            // 打开连接
	            URLConnection con = url.openConnection();
	            // 输入流
	            is = con.getInputStream();
	            // 1K的数据缓冲
	            byte[] bs = new byte[1024];

	           String topimgpath2="C:\\lftx\\imgtemp\\"+ss+".jpg";
	            File file = new File(topimgpath2);
	            int len;
	            os = new FileOutputStream(file, true);
	            while ((len = is.read(bs)) != -1) {
	                os.write(bs, 0, len);
	            }
	            is.close();
	            os.close();
	            
	            
	            
	            System.out.println("newtopimgpath:"+topimgpath2);
	            
				
				 BufferedImage ewm = resizeImage(180,180,ImageIO.read(new File(ewmpath)));
				 BufferedImage  topimg0= ImageIO.read(new File(topimgpath2));
				int topimg0w= topimg0.getWidth();
				double topimg0w2=topimg0w;
				 int  topimg0h=topimg0.getHeight();
				 double topimghw=topimg0h/topimg0w2;
				 System.out.println("首图高："+topimg0h);
				 System.out.println("首图宽："+topimg0w2);
				 System.out.println("首图高宽比："+topimghw);
				 if(topimghw>1) {
					 BufferedImage topimg = resizeImage(800,1000,topimg0);
			    	 
			          /**
			           * 得到图片缓冲区
			           * INT精确度达到一定,RGB三原色，高度280,宽度360
			           */
			          BufferedImage bi = new BufferedImage(800, 1550, BufferedImage.TYPE_INT_RGB);
			          //得到它的绘制环境(这张图片的笔)
			          Graphics2D g2 = (Graphics2D) bi.getGraphics();
			          
			        //填充一个矩形 左上角坐标(0,0),宽70,高150;填充整张图片
			          g2.fillRect(0, 0, 800, 1900);
			          //设置颜色
			          g2.setColor(Color.white);
			          //填充整张图片(其实就是设置背景颜色)
			          g2.fillRect(0, 0, 800, 1900);
			          
			          g2.drawImage(topimg, 0, 0, null);
			    	
			          //设置字体:字体、字号、大小
			         // g2.setFont(new Font("黑体", Font.BOLD, 18));
			          //设置背景颜色
			          //g2.setColor(Color.black);

			          //添加文字 xy坐标位置
			         // g2.drawString(title, 10, 380);
			          
			          drawStringWithFontStyleLineFeed(g2,title+"元",10,1050,new Font("宋体", Font.PLAIN, 32));

			          
			          g2.setFont(new Font("微软雅黑", Font.BOLD, 32));
			          g2.setColor(Color.red);
			          g2.drawString("淘宝|券后价："+price+"元", 10, 1150);
			          
			          
			          g2.setFont(new Font("微软雅黑", Font.BOLD, 32));
			          g2.setColor(Color.gray);
			          g2.drawString("原价："+oprice+"元", 10, 1210);
			          
			          //绘制优惠券背景
			          BufferedImage coupons = resizeImage(800,440,ImageIO.read(new File("C:\\lftx\\coupons.png")));
			          g2.drawImage(coupons, 8, 1240, 780, 240, null);
			          
			         //绘制二维码
			          g2.drawImage(ewm, 570, 1265, 180, 180, null);
			          
			          
			          g2.setFont(new Font("微软雅黑", Font.BOLD, 48));
			          g2.setColor(Color.white);
			          g2.drawString("￥"+coupon+" 优惠券", 80, 1330);
			          

			          
			          g2.setFont(new Font("微软雅黑", Font.PLAIN, 32));
			          g2.setColor(Color.white);
			          g2.drawString("微信内长按识别二维码领券", 80, 1400);
			          
			            Date date2 = new Date();//获取当前的日期
			            SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
			            String str2 = df2.format(date);//获取String类型的时间
			            CharacterUtils  strc2=new CharacterUtils();
			            String ss2=strc2.getRandomString(16);
			          
			          
			            filePath="C:\\lftx\\imgtemp\\"+ss2+".jpg";
			          //保存图片 JPEG表示保存格式
			          ImageIO.write(bi, "JPEG", new FileOutputStream(filePath));


			        
			          System.out.println("成功！");
				 }else {
					 BufferedImage topimg = resizeImage(800,800,topimg0);
			    	 
			          /**
			           * 得到图片缓冲区
			           * INT精确度达到一定,RGB三原色，高度280,宽度360
			           */
			          BufferedImage bi = new BufferedImage(800, 1350, BufferedImage.TYPE_INT_RGB);
			          //得到它的绘制环境(这张图片的笔)
			          Graphics2D g2 = (Graphics2D) bi.getGraphics();
			          
			        //填充一个矩形 左上角坐标(0,0),宽70,高150;填充整张图片
			          g2.fillRect(0, 0, 800, 1600);
			          //设置颜色
			          g2.setColor(Color.white);
			          //填充整张图片(其实就是设置背景颜色)
			          g2.fillRect(0, 0, 800, 1600);
			          
			          g2.drawImage(topimg, 0, 0, null);
			    	
			          //设置字体:字体、字号、大小
			         // g2.setFont(new Font("黑体", Font.BOLD, 18));
			          //设置背景颜色
			          //g2.setColor(Color.black);

			          //添加文字 xy坐标位置
			         // g2.drawString(title, 10, 380);
			          
			          drawStringWithFontStyleLineFeed(g2,title+"元",10,850,new Font("宋体", Font.PLAIN, 32));

			          
			          g2.setFont(new Font("微软雅黑", Font.BOLD, 32));
			          g2.setColor(Color.red);
			          g2.drawString("淘宝|券后价："+price+"元", 10, 950);
			          
			          
			          g2.setFont(new Font("微软雅黑", Font.BOLD, 32));
			          g2.setColor(Color.gray);
			          g2.drawString("原价："+oprice+"元", 10, 1010);
			          
			          //绘制优惠券背景
			          BufferedImage coupons = resizeImage(800,240,ImageIO.read(new File("C:\\lftx\\coupons.png")));
			          g2.drawImage(coupons, 8, 1040, 780, 240, null);
			          
			         //绘制二维码
			          g2.drawImage(ewm, 570, 1065, 180, 180, null);
			          
			          
			          g2.setFont(new Font("微软雅黑", Font.BOLD, 48));
			          g2.setColor(Color.white);
			          g2.drawString("￥"+coupon+" 优惠券", 80, 1130);
			          

			          
			          g2.setFont(new Font("微软雅黑", Font.PLAIN, 32));
			          g2.setColor(Color.white);
			          g2.drawString("微信内长按识别二维码领券", 80, 1200);
			          
			            Date date2 = new Date();//获取当前的日期
			            SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
			            String str2 = df2.format(date);//获取String类型的时间
			            CharacterUtils  strc2=new CharacterUtils();
			            String ss2=strc2.getRandomString(16);
			          
			          
			            filePath="C:\\lftx\\imgtemp\\"+ss2+".jpg";
			          //保存图片 JPEG表示保存格式
			          ImageIO.write(bi, "JPEG", new FileOutputStream(filePath));


			        
			          System.out.println("成功！");
				 }
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   	  
	    	

	    	return filePath;
	    	
	    }
	    
	    
	    public static BufferedImage resizeImage(int x, int y, BufferedImage bfi) {
	        BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
	        bufferedImage.getGraphics().drawImage(bfi.getScaledInstance(x, y, Image.SCALE_SMOOTH), 0, 0, null);
	        return bufferedImage;
	    }
	    
	    
	    private static  void  drawStringWithFontStyleLineFeed(Graphics g, String strContent, int loc_X, int loc_Y, Font font){
	    	//设置背景颜色
	        g.setColor(Color.black);
	    	g.setFont(font);
	        //获取字符串 字符的总宽度
	        int strWidth =getStringLength(g,strContent);
	        //每一行字符串宽度
	        int rowWidth=780;
	        System.out.println("每行字符宽度:"+rowWidth);
	        //获取字符高度
	        int strHeight=getStringHeight(g);
	        //字符串总个数
	        System.out.println("字符串总个数:"+strContent.length());
	        if(strWidth>rowWidth){
	            int rowstrnum=getRowStrNum(strContent.length(),rowWidth,strWidth);
	            int  rows= getRows(strWidth,rowWidth);
	            String temp="";
	            for (int i = 0; i < rows; i++) {
	                //获取各行的String 
	                if(i==rows-1){
	                    //最后一行
	                    temp=strContent.substring(i*rowstrnum,strContent.length());
	                }else{
	                    temp=strContent.substring(i*rowstrnum,i*rowstrnum+rowstrnum);
	                }
	                if(i>0){
	                    //第一行不需要增加字符高度，以后的每一行在换行的时候都需要增加字符高度
	                    loc_Y=loc_Y+strHeight;
	                }
	                g.drawString(temp, loc_X, loc_Y);
	            }
	        }else{
	            //直接绘制
	            g.drawString(strContent, loc_X, loc_Y);
	        }
	        
	    }
	    
	    
	    

	private static int  getStringHeight(Graphics g) {
	        int height = g.getFontMetrics().getHeight();
	        System.out.println("字符高度:"+height);
	        return height;
	    }



	private static int  getStringLength(Graphics g,String str) {
	        char[]  strcha=str.toCharArray();
	        int strWidth = g.getFontMetrics().charsWidth(strcha, 0, str.length());
	        System.out.println("字符总宽度:"+strWidth);
	        return strWidth;
	    }


	private static int getRowStrNum(int strnum,int rowWidth,int strWidth){
	int rowstrnum=0;
	rowstrnum=(rowWidth*strnum)/strWidth;
	System.out.println("每行的字符数:"+rowstrnum);
	return rowstrnum;
	}

	private static  int  getRows(int strWidth,int rowWidth){
	    int rows=0;
	    if(strWidth%rowWidth>0){
	        rows=strWidth/rowWidth+1;
	    }else{
	        rows=strWidth/rowWidth;
	    }
	    System.out.println("行数:"+rows);
	    return rows;
	}


}
