package com.example.graphdmeo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		LineChart lineChart = (LineChart) findViewById(R.id.lineChart);
		
		CharInfo charInfo = new CharInfo();
		charInfo.setTitle("���24Сʱ��������ָ��");
		charInfo.setxScaleNum(6);
		charInfo.setyScaleNum(6);
		String[] yScaleLeftLable = new String[6];
		yScaleLeftLable[0] = "50";
		yScaleLeftLable[1] = "100";
		yScaleLeftLable[2] = "150";
		yScaleLeftLable[3] = "200";
		yScaleLeftLable[4] = "300";
		yScaleLeftLable[5] = "500";
		charInfo.setyScaleLeftLable(yScaleLeftLable);
		
		String[] yScaleRightLable = new String[6];
		yScaleRightLable[0] = "��";
		yScaleRightLable[1] = "��";
		yScaleRightLable[2] = "�����Ⱦ";
		yScaleRightLable[3] = "�ж���Ⱦ";
		yScaleRightLable[4] = "�ض���Ⱦ";
		yScaleRightLable[5] = "������Ⱦ";
		charInfo.setyScaleRightLable(yScaleRightLable);
		
		
		String[] xScaleDownLable = new String[7];
		xScaleDownLable[0] = "21:00";
		xScaleDownLable[1] = "01:00";
		xScaleDownLable[2] = "05:00";
		xScaleDownLable[3] = "09:00";
		xScaleDownLable[4] = "13:00";
		xScaleDownLable[5] = "17:00";
		xScaleDownLable[6] = "21:00";
		charInfo.setxScaleDownLable(xScaleDownLable);
		
		lineChart.setCharInfo(charInfo);
		
		List<LineInfo> lineInfos = new ArrayList<LineInfo>();
		
		LineInfo lineInfo1 = new LineInfo();
		lineInfo1.setPointColor(0xFFE5B814);
		lineInfo1.setLineColor(0xFFC8A724);
		lineInfo1.setName("�������¹�");
		lineInfo1.setPoints(new int[]{570,450,350,250,130,
				170,190,185,177,165,155,150,168,170,190,
				200,210,205,230,220,210,190,180,190,170,
				180});
		lineInfos.add(lineInfo1);
		
//		LineInfo lineInfo2 = new LineInfo();
//		lineInfo2.setPointColor(0xFF06D606);
//		lineInfo2.setLineColor(0xFF99CC00);
//		lineInfo2.setName("�������վ");
//		lineInfo2.setPoints(new int[]{170,150,250,300,170,
//				120,130,165,147,265,255,170,268,180,160,
//				240,220,215,200,210,240,180,190,200,270,
//				190});
//		lineInfos.add(lineInfo2);
		
		lineChart.setLineInfos(lineInfos);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

}
