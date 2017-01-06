/*Programming by zlzsam*/
package com.example.calculatorpro;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

	View vi;
	String str = "", secstr = "";
	EditText primaryET;
	/* EditText secondaryET; */
	int flag = 0, c = 0;
	double b = 0.0, g = 0.0, f = 0.0;

	// 对输出的数进行规范化
	public void substr() {
		int a = str.indexOf(".");//找.来返回位置的整数值
		int b = str.indexOf("E");
		String tempstr = str;
		if (a == -1) {
			if(str.length()>12)
			str = str.substring(0, 12);
		}
		if (a > 0) {
			if (b == -1) {
				if(str.length()>12)
				str = str.substring(0, 12);
			}
			if (b > 0) {
				tempstr = str.substring(b);//从b取到最后
				if (str.length() > 12) {
					int perStrLen = 12 - tempstr.length();
					str = str.substring(0, perStrLen) + tempstr;
				} 
			}
		}

	}

	// 计算方法
	public double calc() {
		switch (c) {
		case 0:
			f = g;
			break;
		case 1:
			f = b + g;
			break;
		case 2:
			f = b - g;
			break;
		case 3:
			f = b * g;
			break;
		case 4:
			f = b / g;
			break;
		}

		b = f;
		c = 0;

		return f;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 获取所有按键，保存到数组当中去
		final Button number[] = new Button[10];
		final Button mark[] = new Button[13];

		// 获取功能按钮
		mark[0] = (Button) findViewById(R.id.markone);
		mark[1] = (Button) findViewById(R.id.marktwo);
		mark[2] = (Button) findViewById(R.id.markthree);
		mark[3] = (Button) findViewById(R.id.markfour);
		mark[4] = (Button) findViewById(R.id.markfive);
		mark[5] = (Button) findViewById(R.id.marksix);
		mark[6] = (Button) findViewById(R.id.markseven);
		mark[7] = (Button) findViewById(R.id.markeight);
		mark[8] = (Button) findViewById(R.id.marknine);
		mark[9] = (Button) findViewById(R.id.markten);
		mark[10] = (Button) findViewById(R.id.markeleven);
		mark[11] = (Button) findViewById(R.id.marktwelve);
		mark[12] = (Button) findViewById(R.id.markthreeteen);

		// 获取数字按钮
		number[0] = (Button) findViewById(R.id.btnzero);
		number[1] = (Button) findViewById(R.id.btnone);
		number[2] = (Button) findViewById(R.id.btntwo);
		number[3] = (Button) findViewById(R.id.btnthree);
		number[4] = (Button) findViewById(R.id.btnfour);
		number[5] = (Button) findViewById(R.id.btnfive);
		number[6] = (Button) findViewById(R.id.btnsix);
		number[7] = (Button) findViewById(R.id.btnseven);
		number[8] = (Button) findViewById(R.id.btneight);
		number[9] = (Button) findViewById(R.id.btnnine);

		// 获取主显示屏
		primaryET = (EditText) findViewById(R.id.edittext2_show);
 
		primaryET.setText(str); 

		// 对C按钮的事件的处理
		mark[0].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b = 0.0;
				c = 0;
				g = 0.0;
				str = "";
				primaryET.setText(str);
				secstr = ""; 
			}
		});

		// 对CE按钮的事件的处理
		mark[1].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				str = "";
				primaryET.setText(str);
				vi = v;
			}
		});

		// 对平方的事件的处理
		mark[2].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (str != "") {
					double temp = Double.parseDouble(str);
					str = "" + temp * temp;
					substr();
					primaryET.setText(str);
				}
			}
		});

		// 对根号的事件的处理
		mark[3].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (str != "") {
					double temp = Double.parseDouble(str);
					str = Math.sqrt(temp) + "";
					substr();
					primaryET.setText(str);
				}
			}
		});
		// 对backspace的事件的处理
		mark[4].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (str != "") {
					if(str.length()==1){
						str="";
						primaryET.setText(str);
					}
					else{
						str = str.substring(0, str.length() - 1);//返回一个新字符串，它是此字符串的一个子字符串。
						//String str = "123456789"; str =str.substring(1,3) 结果：str="23"
						primaryET.setText(str);
					}
					
				}
			 
				else {
					primaryET.setText(str);
				}
			}
		});

		// 对正负号的事件的处理
		mark[5].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (vi != mark[11] && str != "") {//不是小数点
					char ch = str.charAt(0);
					if (ch == '-')
						str = str.replace("-", "");
					else
						str = "-" + str;
					primaryET.setText(str);
				}
			}
		});

		// 对求1/x事件的处理
		mark[6].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (str != "") {
					Double d = Double.parseDouble(str);
					Double s = 1.0 / d;
					str = "" + s.toString();
					substr();
					primaryET.setText(str);
				} else {
					primaryET.setText("0");
				}
			}
		});

		// 对除号的事件的处理
		mark[7].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (str != "") {
					if (vi == mark[7] || vi == mark[8] || vi == mark[9]
							|| vi == mark[10]) {
						c = 4;
					} else {
						g = Double.parseDouble(str);
						calc();
						str = "" + f;
						substr();
						primaryET.setText(str);
						c = 4;
						flag = 1;
						vi = v;
					}
				}
			}
		});

		// 对乘号的事件的处理
		mark[8].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (str != "") {
					if (vi == mark[7] || vi == mark[8] || vi == mark[9]
							|| vi == mark[10]) {
						c = 3;
					} else {
						g = Double.parseDouble(str);
						calc();
						str = "" + f;
						substr();
						primaryET.setText(str);
						c = 3;
						flag = 1;
						vi = v;
					}
				}
			}
		});

		// 对减号的事件的处理
		mark[9].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (str != "") {
					if (vi == mark[7] || vi == mark[8] || vi == mark[9]
							|| vi == mark[10]) {
						c = 2;
					} else {
						g = Double.parseDouble(str);
						calc();
						str = "" + f;
						substr();
						primaryET.setText(str);
						c = 2;
						flag = 1;
						vi = v;
					}
				}
			}
		});

		// 对加号的事件的处理
		mark[10].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (str != "") {//首先判断是否是加减乘除的运算符
					if (vi == mark[7] || vi == mark[8] || vi == mark[9]|| vi == mark[10]) {
						c = 1;//如果是加减乘除运算符则C为1
					} else {
						g = Double.parseDouble(str);//表示将从字符串（数组也可以）中得到的值转换成Double类型的数据 
						calc();
						str = "" + f;
						substr();
						primaryET.setText(str);
						c = 1;
						flag = 1;
						vi = v;
					}
				}
			}
		});

		// 对小数点的处理
		mark[11].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (str == "") {
					str += ".";
					primaryET.setText(str);
				} else {
					char ch[];
					int x = 0;
					ch = str.toCharArray();//将字符串对象中的字符转换为一个字符数组
					for (int i = 0; i < ch.length; i++)
						if (ch[i] == '.')
							x++;
					if (x == 0) {
						str += ".";//字符串后面加上小数点
						primaryET.setText(str);
					}

				}
			}
		});

		// 对等号的事件的处理
		mark[12].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (str != "" && vi != mark[7] && vi != mark[8]
						&& vi != mark[9] && vi != mark[10]) {
					g = Double.parseDouble(str);
					calc();
					str = "" + f;
					substr();
					primaryET.setText(str);
					flag = 1;
					vi = v;

				}
			}
		});
		// 设定数字按键
		number[0].setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (flag == 1) {
					str = "";
					str += 0;
					primaryET.setText(str);
					flag = 0;
				} else {
					char ch1[];
					ch1 = str.toCharArray();
					if (!(ch1.length == 1 && ch1[0] == '0')) {
						str += 0;
						primaryET.setText(str);
					}

				}
				vi = v;
			}
		});

		number[1].setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (flag == 1) {
					str = "";
					str += 1;
					primaryET.setText(str);
					flag = 0;
				} else {
					str += 1;
					primaryET.setText(str);
				}
				vi = v;
			}
		});

		number[2].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (flag == 1) {
					str = "";
					str += 2;
					primaryET.setText(str);
					flag = 0;
				} else {
					str += 2;
					primaryET.setText(str);
				}
				vi = v;
			}
		});

		number[3].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (flag == 1) {
					str = "";
					str += 3;
					primaryET.setText(str);
					flag = 0;
				} else {
					str += 3;
					primaryET.setText(str);
				}
				vi = v;
			}
		});

		number[4].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (flag == 1) {
					str = "";
					str += 4;
					primaryET.setText(str);
					flag = 0;
				} else {
					str += 4;
					primaryET.setText(str);
				}
				vi = v;
			}
		});

		number[5].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (flag == 1) {
					str = "";
					str += 5;
					primaryET.setText(str);
					flag = 0;
				} else {
					str += 5;
					primaryET.setText(str);
				}
				vi = v;
			}
		});

		number[6].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (flag == 1) {
					str = "";
					str += 6;
					primaryET.setText(str);
					flag = 0;
				} else {
					str += 6;
					primaryET.setText(str);
				}
				vi = v;
			}
		});

		number[7].setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (flag == 1) {
					str = "";
					str += 7;
					primaryET.setText(str);
					flag = 0;
				} else {
					str += 7;
					primaryET.setText(str);
				}
				vi = v;
			}
		});

		number[8].setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (flag == 1) {
					str = "";
					str += 8;
					primaryET.setText(str);
					flag = 0;
				} else {
					str += 8;
					primaryET.setText(str);
				}
				vi = v;
			}
		});

		number[9].setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (flag == 1) {
					str = "";
					str += 9;
					primaryET.setText(str);
					flag = 0;
				} else {
					str += 9;
					primaryET.setText(str);
				}
				vi = v;
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
