package com;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import cn.hutool.core.swing.clipboard.ImageSelection;
import common.CharacterUtils;
import common.Doonetime;
import common.HttpUtil;
import common.WxUtil;

import javax.swing.JPanel;

import java.awt.AWTException;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.Robot;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import javax.swing.SwingConstants;

public class Startmain {

	private JFrame frame;
	private JTextField gjctextField;
	private JTextField idtextField;
	// 创建任务队列   10 为线程数量      
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10); 
    private JTextField gltextField;
    private JTextField startprice;
    private JTextField endprice;
	

	/**
	 * Launch the application. 22
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("程序启动");
					Startmain window = new Startmain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Startmain() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setResizable(false);
		frame.setBounds(100, 100, 1016, 872);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label = new JLabel("\u9886\u4ED8\u5929\u4E0B\u5FAE\u4FE1\u81EA\u52A8\u4F18\u60E0\u5238\u63A8\u9001\u7CFB\u7EDF");
		label.setBounds(32, 28, 445, 44);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		
		JLabel lblid = new JLabel("\u5FAE\u4FE1\u626B\u7801\u8FDB\u5165\u516C\u4F17\u53F7\uFF1A\u9886\u4ED8\u5929\u4E0B\uFF0C\u70B9\u51FB\u6211\u7684\u8FDB\u884C\u767B\u5F55\u6388\u6743\u83B7\u53D6\u7528\u6237id");
		lblid.setForeground(Color.BLACK);
		lblid.setBounds(32, 78, 420, 16);
		lblid.setFont(new Font("黑体", Font.PLAIN, 12));
		
		JPanel panel = new JPanel();
		panel.setBounds(190, 104, 292, 38);
		panel.setBackground(Color.WHITE);
		
		JLabel label_1 = new JLabel("\u5173\u952E\u8BCD\uFF1A");
		label_1.setBounds(32, 151, 120, 36);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		
		JLabel label_2 = new JLabel("\u5E73\u53F0\uFF1A");
		label_2.setBounds(32, 104, 150, 40);
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		
		JLabel lblid_3 = new JLabel("\u7528\u6237ID\uFF1A");
		lblid_3.setBounds(32, 320, 129, 36);
		lblid_3.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		
		gjctextField = new JTextField();
		gjctextField.setBounds(190, 152, 292, 34);
		gjctextField.setText("\u5973\u88C5");
		gjctextField.setColumns(10);
		
		idtextField = new JTextField();
		idtextField.setBounds(190, 320, 292, 34);
		idtextField.setColumns(10);
		
		JLabel label_1_1_1 = new JLabel("\u5FAE\u4FE1\u7FA4\u540D\u79F0\u5217\u8868(\u6700\u591A\u4E94\u4E2A)\uFF1A");
		label_1_1_1.setBounds(32, 360, 420, 36);
		label_1_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		
		JTextArea wxqtextArea = new JTextArea();
		wxqtextArea.setBounds(32, 440, 445, 65);
		wxqtextArea.setText("\u6587\u4EF6\u4F20\u8F93\u52A9\u624B");
		
		JLabel lblid_1 = new JLabel("\u591A\u5FAE\u4FE1\u7FA4\u793A\u4F8B:(\u5FAE\u4FE1\u7FA4\u540D\u79F01|\u5FAE\u4FE1\u7FA4\u540D\u79F02|\u5FAE\u4FE1\u7FA4\u540D\u79F03)");
		lblid_1.setBounds(32, 410, 334, 16);
		lblid_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 12));
		

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(518, 28, 469, 172);
		panel_1.setBackground(Color.WHITE);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(518, 210, 469, 136);
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(null);
		
		JLabel label_5 = new JLabel("\u6536\u76CA\u6A21\u5F0F\uFF1A");
		label_5.setBounds(10, 10, 82, 15);
		panel_2.add(label_5);
		
		JLabel label_5_1 = new JLabel("1.\u5FAE\u4FE1\u7FA4\u6709\u4EBA\u70B9\u51FB\u5206\u4EAB\u4E0B\u5355\u5C31\u6709\u6536\u76CA");
		label_5_1.setBounds(10, 35, 400, 15);
		panel_2.add(label_5_1);
		
		JLabel label_5_1_1 = new JLabel("2.\u7ED1\u5B9A\u8FC7\u7684\u4EBA\u518D\u6B64\u4E0B\u5355\u4E5F\u53EF\u83B7\u53D6\u8BA2\u5355\u6536\u76CA");
		label_5_1_1.setBounds(10, 60, 400, 15);
		panel_2.add(label_5_1_1);
		
		JLabel label_5_1_1_1 = new JLabel("\u7ED1\u5B9A\u6A21\u5F0F\u4ECB\u7ECD\uFF1A");
		label_5_1_1_1.setBounds(10, 85, 210, 15);
		panel_2.add(label_5_1_1_1);
		
		JLabel label_5_1_1_1_1 = new JLabel("\u7B2C\u4E00\u6B21\u70B9\u51FB\u5206\u4EAB\u94FE\u63A5\u7684\u4EBA\u5C06\u4E0E\u5206\u4EAB\u8005\u7ED1\u5B9A,\u53EF\u83B7\u53D6\u5176\u4E4B\u540E\u6240\u6709\u8BA2\u5355\u7684\u8BA2\u5355\u4F63\u91D1");
		label_5_1_1_1_1.setBounds(10, 110, 449, 15);
		panel_2.add(label_5_1_1_1_1);
		panel_1.setLayout(null);
		
		JLabel label_3 = new JLabel("\u4F7F\u7528\u8BF4\u660E\uFF1A");
		label_3.setFont(new Font("黑体", Font.PLAIN, 12));
		label_3.setBounds(10, 10, 102, 15);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("1.\u7535\u8111\u767B\u5F55\u5FAE\u4FE1\u5BA2\u6237\u7AEF");
		label_4.setBounds(10, 35, 161, 15);
		panel_1.add(label_4);
		
		JLabel lblid_2 = new JLabel("2.\u8BBE\u7F6E\u5DE6\u4FA7\u5185\u5BB9\uFF08\u9886\u4ED8\u5929\u4E0Bid\u9700\u8981\u4FEE\u6539\u4E3A\u81EA\u5DF1\u7684\uFF09");
		lblid_2.setBounds(10, 60, 366, 15);
		panel_1.add(lblid_2);
		
		JLabel lblid_2_1 = new JLabel("3.\u70B9\u51FB\u542F\u52A8\uFF0C\u7CFB\u7EDF\u81EA\u52A8\u5206\u4EAB\u9AD8\u4F63\u5546\u54C1\u5230\u5FAE\u4FE1\u7FA4\uFF0C\u671F\u95F4\u7535\u8111\u4E0D\u53EF\u8FDB\u884C\u5176\u4ED6\u64CD\u4F5C");
		lblid_2_1.setBounds(10, 85, 449, 15);
		panel_1.add(lblid_2_1);
		
		JLabel lblid_2_1_1 = new JLabel("4.\u5982\u9700\u505C\u6B62\uFF0C\u8BF7\u5728\u7CFB\u7EDF\u6CA1\u6709\u81EA\u52A8\u64CD\u4F5C\u65F6\u70B9\u51FB\u5DE6\u4FA7\u505C\u6B62\u6309\u94AE");
		lblid_2_1_1.setBounds(10, 110, 402, 15);
		panel_1.add(lblid_2_1_1);
		panel.setLayout(null);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(panel_2);
		frame.getContentPane().add(label);
		frame.getContentPane().add(label_2);
		frame.getContentPane().add(panel);
		
		JRadioButton tbradioButton = new JRadioButton("\u6DD8\u5B9D\u5E73\u53F0");
		tbradioButton.setSelected(true);
		tbradioButton.setBounds(14, 8, 260, 23);
		panel.add(tbradioButton);
		
		
		ButtonGroup ptgroup=new ButtonGroup();
		ptgroup.add(tbradioButton);
		
		frame.getContentPane().add(label_1);
		frame.getContentPane().add(gjctextField);
		frame.getContentPane().add(lblid);
		frame.getContentPane().add(label_1_1_1);
		frame.getContentPane().add(lblid_3);
		frame.getContentPane().add(idtextField);
		frame.getContentPane().add(lblid_1);
		frame.getContentPane().add(wxqtextArea);

		frame.getContentPane().add(panel_1);
		
		JButton gwbutton = new JButton("\u9886\u4ED8\u5929\u4E0B\u5B98\u7F51");
		gwbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String file = "https://hlapi.top";
				String cmd = "cmd.exe /c start ";
				try {

				//执行操作

				Runtime.getRuntime().exec(cmd + file);

				} catch (IOException ignore) {

				//打印异常

				ignore.printStackTrace();

				}
				
			}
		});
		gwbutton.setBackground(Color.WHITE);
		gwbutton.setBounds(286, 6, 126, 23);
		panel_1.add(gwbutton);
		
		JLabel lblid_2_1_1_1 = new JLabel("5.\u9700\u8981\u81EA\u5DF1\u521B\u5EFA\u5FAE\u4FE1\u7FA4\uFF0C\u4E0D\u8981\u5411\u4ED6\u4EBA\u5FAE\u4FE1\u7FA4\u63A8\u9001\u6D88\u606F\uFF0C\u5BB9\u6613\u88AB\u6295\u8BC9\u5C01\u53F7");
		lblid_2_1_1_1.setBounds(10, 135, 449, 15);
		panel_1.add(lblid_2_1_1_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(518, 481, 224, 200);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel label_6 = new JLabel("\u5982\u4F55\u67E5\u770B\u8BA2\u5355\uFF1A");
		label_6.setBounds(10, 10, 155, 15);
		panel_3.add(label_6);
		
		JLabel label_6_1 = new JLabel("\u6253\u5F00\u516C\u4F17\u53F7--\u5B98\u7F51--\u6211\u7684--\u63A8\u5E7F\u8BA2\u5355");
		label_6_1.setBounds(10, 34, 204, 15);
		panel_3.add(label_6_1);
		
		JLabel label_6_1_1 = new JLabel("\u8F6F\u4EF6\u4F18\u52BF\uFF1A");
		label_6_1_1.setBounds(10, 59, 204, 15);
		panel_3.add(label_6_1_1);
		
		JLabel label_6_1_1_1 = new JLabel("<html>\u672C\u8F6F\u4EF6\u6838\u5FC3\u662F\u5206\u53D1\u4F18\u60E0\u5238\u4E1A\u52A1\uFF0C\u501F\u52A9\u6DD8\u5B9D/\u4EAC\u4E1C\u7B49\u5927\u5E73\u53F0\uFF0C\u5B9E\u73B0\u5185\u90E8\u4F18\u60E0\u5238\u5206\u53D1\uFF0C\u5185\u90E8\u4F18\u60E0\u5238\u662F\u6DD8\u5B9D\u5546\u5BB6\u4E3A\u4E86\u63D0\u9AD8\u9500\u91CF\u53EA\u9488\u5BF9\u63A8\u5E7F\u8005\u53D1\u5E03\u7684\u4F18\u60E0\u5238\uFF0C\u901A\u8FC7\u5185\u90E8\u4F18\u60E0\u5238\u7528\u6237\u53EF\u4EE5\u4F7F\u7528\u66F4\u52A0\u4F4E\u7684\u4EF7\u683C\u8D2D\u4E70\u5546\u54C1\uFF01</html>");
		label_6_1_1_1.setBounds(10, 69, 204, 110);
		panel_3.add(label_6_1_1_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(518, 362, 469, 109);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JLabel label_7 = new JLabel("\u7ED3\u7B97\u8BF4\u660E\uFF1A");
		label_7.setBounds(10, 10, 93, 15);
		panel_4.add(label_7);
		
		JLabel label_7_1 = new JLabel("1.\u8BA2\u535520\u5206\u949F\u66F4\u65B01\u6B21\uFF0C\u4E0B\u5355\u540E\u4E0D\u8981\u7740\u6025\uFF0C\u53EF20-30\u5206\u949F\u540E\u5237\u65B0\u5C0F\u7A0B\u5E8F\u67E5\u770B");
		label_7_1.setBounds(10, 31, 400, 15);
		panel_4.add(label_7_1);
		
		JLabel label_7_1_1 = new JLabel("2.\u8BF7\u4E00\u5B9A\u5728\u5C0F\u7A0B\u5E8F\u6211\u7684\u4E2A\u4EBA\u4FE1\u606F\u5904\u7ED1\u5B9A\u652F\u4ED8\u5B9D\u624B\u673A\u53F7\uFF0C\u4E0D\u7136\u65E0\u6CD5\u6B63\u5E38\u7ED3\u7B97");
		label_7_1_1.setBounds(10, 56, 400, 15);
		panel_4.add(label_7_1_1);
		
		JLabel label_7_1_1_1 = new JLabel("3.\u6BCF\u670825-30\u53F7\u5DE6\u53F3\u7ED3\u7B97\u6536\u76CA\uFF0C\u8BF7\u8010\u5FC3\u7B49\u5F85\uFF01");
		label_7_1_1_1.setBounds(10, 81, 400, 15);
		panel_4.add(label_7_1_1_1);
		
		JLabel ewm = new JLabel(new ImageIcon("static/gzh100.jpg"));
		ewm.setBounds(787, 481, 200, 200);
		frame.getContentPane().add(ewm);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(32, 560, 445, 63);
		frame.getContentPane().add(panel_5);
		panel_5.setLayout(null);
		
		JCheckBox checkBox7 = new JCheckBox("7\u70B9");
		checkBox7.setBounds(6, 6, 59, 23);
		panel_5.add(checkBox7);
		
		JCheckBox checkBox8 = new JCheckBox("8\u70B9");
		checkBox8.setBounds(70, 5, 58, 23);
		panel_5.add(checkBox8);
		
		JCheckBox checkBox9 = new JCheckBox("9\u70B9");
		checkBox9.setSelected(true);
		checkBox9.setBounds(137, 6, 55, 23);
		panel_5.add(checkBox9);
		
		JCheckBox checkBox10 = new JCheckBox("10\u70B9");
		checkBox10.setSelected(true);
		checkBox10.setBounds(197, 5, 55, 23);
		panel_5.add(checkBox10);
		
		JCheckBox checkBox11 = new JCheckBox("11\u70B9");
		checkBox11.setSelected(true);
		checkBox11.setBounds(258, 6, 56, 23);
		panel_5.add(checkBox11);
		
		JCheckBox checkBox12 = new JCheckBox("12\u70B9");
		checkBox12.setSelected(true);
		checkBox12.setBounds(317, 8, 57, 23);
		panel_5.add(checkBox12);
		
		JCheckBox checkBox13 = new JCheckBox("13\u70B9");
		checkBox13.setBounds(380, 7, 55, 23);
		panel_5.add(checkBox13);
		
		JCheckBox checkBox14 = new JCheckBox("14\u70B9");
		checkBox14.setBounds(6, 32, 59, 23);
		panel_5.add(checkBox14);
		
		JCheckBox checkBox15 = new JCheckBox("15\u70B9");
		checkBox15.setBounds(69, 32, 60, 23);
		panel_5.add(checkBox15);
		
		JCheckBox checkBox16 = new JCheckBox("16\u70B9");
		checkBox16.setBounds(137, 32, 55, 23);
		panel_5.add(checkBox16);
		
		JCheckBox checkBox17 = new JCheckBox("17\u70B9");
		checkBox17.setBounds(197, 32, 56, 23);
		panel_5.add(checkBox17);
		
		JCheckBox checkBox18 = new JCheckBox("18\u70B9");
		checkBox18.setSelected(true);
		checkBox18.setBounds(258, 32, 55, 23);
		panel_5.add(checkBox18);
		
		JCheckBox checkBox19 = new JCheckBox("19\u70B9");
		checkBox19.setSelected(true);
		checkBox19.setBounds(317, 33, 55, 23);
		panel_5.add(checkBox19);
		
		JCheckBox checkBox20 = new JCheckBox("20\u70B9");
		checkBox20.setSelected(true);
		checkBox20.setBounds(380, 34, 55, 23);
		panel_5.add(checkBox20);
		
		JLabel label_1_1_1_1 = new JLabel("\u6267\u884C\u65F6\u95F4\uFF1A");
		label_1_1_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		label_1_1_1_1.setBounds(32, 515, 240, 36);
		frame.getContentPane().add(label_1_1_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 705, 977, 128);
		frame.getContentPane().add(scrollPane);
		
		JTextArea myconsole = new JTextArea();
		scrollPane.setViewportView(myconsole);
		
		JLabel gllabel_1 = new JLabel("\u4EF7\u683C\u533A\u95F4\uFF1A");
		gllabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		gllabel_1.setBounds(32, 263, 150, 36);
		frame.getContentPane().add(gllabel_1);
		
		startprice = new JTextField();
		startprice.setText("3");
		startprice.setColumns(10);
		startprice.setBounds(190, 263, 129, 34);
		frame.getContentPane().add(startprice);
		
		endprice = new JTextField();
		endprice.setText("200");
		endprice.setColumns(10);
		endprice.setBounds(353, 263, 129, 34);
		frame.getContentPane().add(endprice);
		
		JLabel label_8 = new JLabel("-");
		label_8.setBounds(330, 273, 11, 15);
		frame.getContentPane().add(label_8);
		
		
		JLabel gllabel = new JLabel("\u8FC7\u6EE4\u6761\u4EF6\uFF1A");
		gllabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		gllabel.setBounds(32, 197, 150, 36);
		frame.getContentPane().add(gllabel);
		
		gltextField = new JTextField();
		gltextField.setText("\u8001\u5E74");
		gltextField.setColumns(10);
		gltextField.setBounds(190, 196, 292, 34);
		frame.getContentPane().add(gltextField);
		
		
		JButton startNewButton = new JButton("\u542F\u52A8");
		startNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myconsole.setText("");
				

				String gjc=gjctextField.getText();
				String userid=idtextField.getText();
				String wxqlist=wxqtextArea.getText();
				String gltj=gltextField.getText();
				String start_price=startprice.getText();
				String end_price=endprice.getText();
				List<Integer> dotime = new ArrayList<>();
				if(checkBox7.isSelected()) {
					dotime.add(7);
				}
				
				if(checkBox8.isSelected()) {
					dotime.add(8);
				}
				if(checkBox9.isSelected()) {
					dotime.add(9);
				}
				
				if(checkBox10.isSelected()) {
					dotime.add(10);
				}
				
				if(checkBox11.isSelected()) {
					dotime.add(11);
				}
				if(checkBox12.isSelected()) {
					dotime.add(12);
				}
				if(checkBox13.isSelected()) {
					dotime.add(13);
				}
				if(checkBox14.isSelected()) {
					dotime.add(14);
				}
				if(checkBox15.isSelected()) {
					dotime.add(15);
				}
				if(checkBox16.isSelected()) {
					dotime.add(16);
				}
				if(checkBox17.isSelected()) {
					dotime.add(17);
				}
				if(checkBox18.isSelected()) {
					dotime.add(18);
				}
				if(checkBox19.isSelected()) {
					dotime.add(19);
				}
				if(checkBox20.isSelected()) {
					dotime.add(20);
				}
				if(end_price.isEmpty()||start_price.isEmpty()||gjc.isEmpty()||userid.isEmpty()||wxqlist.isEmpty()||dotime.size()<1) {
					JOptionPane.showMessageDialog(null, "程序启动失败！请检查配置项目是否为空", "标题", JOptionPane.PLAIN_MESSAGE);
				}else {
					myconsole.append("程序正在运行"+"\n");
					String  pt="淘宝";
					
					tbradioButton.setEnabled(false);
					myconsole.append("选择平台："+pt+"\n");
					
					gjctextField.setEnabled(false);
					myconsole.append("筛选关键词："+gjc+"\n");
					
					
					idtextField.setEnabled(false);
					myconsole.append("用户id："+userid+"\n");
					
					
					wxqtextArea.setEnabled(false);
					myconsole.append("微信群："+wxqlist+"\n");
					
					checkBox7.setEnabled(false);
					checkBox8.setEnabled(false);
					checkBox9.setEnabled(false);
					checkBox10.setEnabled(false);
					checkBox11.setEnabled(false);
					checkBox12.setEnabled(false);
					checkBox13.setEnabled(false);
					checkBox14.setEnabled(false);
					checkBox15.setEnabled(false);
					checkBox16.setEnabled(false);
					checkBox17.setEnabled(false);
					checkBox18.setEnabled(false);
					checkBox19.setEnabled(false);
					checkBox20.setEnabled(false);
					
					gltextField.setEnabled(false);
					
					startprice.setEnabled(false);
					endprice.setEnabled(false);
		
					myconsole.append("计划时间："+dotime.toString()+"\n");
					exetask(wxqlist, dotime, gjc, userid,gltj,start_price,end_price);
					
				}
				

				
			}
		});
		startNewButton.setBounds(60, 640, 179, 34);
		
		JButton btnNewButton_1 = new JButton("\u505C\u6B62");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myconsole.setText("");
				myconsole.append("程序已停止"+"\n");
				
				gjctextField.setEnabled(true);
				
				tbradioButton.setEnabled(true);
				idtextField.setEnabled(true);
				wxqtextArea.setEnabled(true);
				checkBox7.setEnabled(true);
				checkBox8.setEnabled(true);
				checkBox9.setEnabled(true);
				checkBox10.setEnabled(true);
				checkBox11.setEnabled(true);
				checkBox12.setEnabled(true);
				checkBox13.setEnabled(true);
				checkBox14.setEnabled(true);
				checkBox15.setEnabled(true);
				checkBox16.setEnabled(true);
				checkBox17.setEnabled(true);
				checkBox18.setEnabled(true);
				checkBox19.setEnabled(true);
				checkBox20.setEnabled(true);
				gltextField.setEnabled(true);
				startprice.setEnabled(true);
				endprice.setEnabled(true);
				stopsend();
			}
		});
		btnNewButton_1.setBounds(273, 640, 179, 34);
		frame.getContentPane().add(startNewButton);
		frame.getContentPane().add(btnNewButton_1);
		

		

	
	}
	

	
    
    public void stopsend() {
    	if (scheduledExecutorService != null) {
            System.out.println("close the schedule threadpool!");
            scheduledExecutorService.shutdown();
            scheduledExecutorService= Executors.newScheduledThreadPool(10); 
            System.out.println("任务停止");
        }
    	
    	
    }
    
    
    //执行定时任务
    public void   exetask(String wxq,List<Integer> dotime,String gjc,String  userid,String gltj,String start_price,String end_price) {
    	 
    	String res="任务执行成功";

       // 执行任务      
       scheduledExecutorService.scheduleAtFixedRate(() -> {          
       	System.out.println("-----定时器-----");
       	
	       // String friendNickName = "超级购物薅羊毛群";
        
    	System.out.println("本次发送目标："+wxq);
	        try {
	        	Calendar c = Calendar.getInstance();
   	        	int  h=c.get(Calendar.HOUR_OF_DAY);
   	        	System.out.println("本次sen2时间："+h);
				System.out.println("定时任务执行开始");
				boolean istime=false;
				for (int i = 0; i < dotime.size(); i++) {
					if(h==dotime.get(i)) {
						istime=true;
					}
				}
				System.out.println("判断是否在计划时间："+istime);
				if(istime) {
					//定时任务事件
					Doonetime  doontime=new Doonetime();
					try {
						doontime.senwx(wxq, gjc, userid,gltj,start_price,end_price);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}   
       }, 5,300, TimeUnit.SECONDS); // 1s 后开始执行，每 3s 执行一次
       
      // return res;
       
    }
}
