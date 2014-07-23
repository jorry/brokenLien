package com.example.graphdmeo;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * 折线图控件
 * 
 * @author 轻指飞扬
 * @version 1.0
 */
public class LineChart extends View {

	Typeface font = Typeface.create("宋体", Typeface.NORMAL);
	Typeface blodFont = Typeface.create("宋体", Typeface.BOLD);

	private CharInfo charInfo;// 图表信息
	private List<LineInfo> lineInfos;// 折线信息

	public LineChart(Context context) {
		super(context);
	}

	public LineChart(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public LineChart(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(20);
		paint.setTypeface(blodFont);
		canvas.drawText(charInfo.getTitle(), 100, 20, paint);

		int ySpliteNum = charInfo.getyScaleNum();
		int xSpliteNum = charInfo.getxScaleNum();

		// X轴信息
		int xLineXStartCoord = this.getLeft() + 12;
		int xLineXEndCoord = this.getRight() - 50;
		
		
		int xLineYStartCoord = this.getBottom() - 70;
		int xLineYEndCoord = xLineYStartCoord;
		int xLineLength = xLineXEndCoord - xLineXStartCoord;

		// Y轴信息
		int yLineXStartCoord = xLineXStartCoord;
		int yLineXEndCoord = xLineXStartCoord;
		
		int yLineYStartCoord = xLineYStartCoord;
		int yLineYEndCoord = this.getTop() + 50;
		
		int yLineLength = yLineYStartCoord > yLineYEndCoord == true ? yLineYStartCoord
				- yLineYEndCoord
				: yLineYEndCoord - yLineYStartCoord;

		// 画坐标轴
		paint.setStrokeWidth(2);
		canvas.drawLine(xLineXStartCoord, xLineYStartCoord, xLineXEndCoord,
				xLineYEndCoord, paint);
		canvas.drawLine(yLineXStartCoord, yLineYStartCoord, yLineXEndCoord,
				yLineYEndCoord, paint);

		// 画X轴(时间轴)刻度
		paint.setColor(0xCCCCCCFF);
		Integer[] xPoint = new Integer[xSpliteNum];
		int xStep = xLineLength / xSpliteNum;
		int xLine = xLineXStartCoord;
		for (int i = 0; i < xSpliteNum; i++) {
			xLine += xStep;
			xPoint[i] = xLine;
			canvas.drawLine(xLine, yLineYStartCoord, xLine, yLineYEndCoord, paint);
		}

		String[] xScaleDownLabel = charInfo.getxScaleDownLable();
		for (int i = 0; i < xPoint.length; i++) {
			paint.setColor(Color.BLACK);
			paint.setTextSize(14);
			paint.setTypeface(font);
			paint.setTextAlign(Align.RIGHT);
			if (i == 0) {
				canvas.drawText(xScaleDownLabel[i], yLineXStartCoord,
						xLineYStartCoord + 15, paint);
				canvas.drawText(xScaleDownLabel[i + 1], xPoint[i],
						xLineYStartCoord + 15, paint);
			} else {
				canvas.drawText(xScaleDownLabel[i + 1], xPoint[i],
						xLineYStartCoord + 15, paint);
			}
		}

		String[] yScaleLeftLabel = charInfo.getyScaleLeftLable();
		String[] yScaleRightLabel = charInfo.getyScaleRightLable();
		// Y轴线划分
		Integer[] yPoint = new Integer[ySpliteNum];// 6个
		int yStep = yLineLength / ySpliteNum;
		int yLine = yLineYStartCoord;
		for (int i = 0; i < ySpliteNum; i++) {
			if (i == ySpliteNum - 1) {
				yLine = yLineYEndCoord;
			} else {
				yLine -= yStep;
			}
			yPoint[i] = yLine; //
			 canvas.drawLine(yLineXStartCoord, yLine, yLineXStartCoord
			 + xLineLength, yLine, paint);
		}

		// Y轴色块、数字
		for (int i = 0; i < yPoint.length; i++) {
			paint.setStrokeWidth(3);
			paint.setColor(Color.BLACK);
			switch (i) {
			case 0:
				canvas.drawLine(yLineXStartCoord, xLineYStartCoord,
						yLineXStartCoord, yPoint[i], paint);

				paint.setTextSize(14);
				paint.setTypeface(font);
				paint.setTextAlign(Align.RIGHT);
				canvas.drawText("0", yLineXStartCoord - 6, xLineYStartCoord,
						paint);
				break;
			case 1:
				canvas.drawLine(yLineXStartCoord, yPoint[i - 1],
						yLineXStartCoord, yPoint[i], paint);
				break;
			case 2:
				canvas.drawLine(yLineXStartCoord, yPoint[i - 1],
						yLineXStartCoord, yPoint[i], paint);
				break;
			case 3:
				canvas.drawLine(yLineXStartCoord, yPoint[i - 1],
						yLineXStartCoord, yPoint[i], paint);
				break;
			case 4:
				canvas.drawLine(yLineXStartCoord, yPoint[i - 1],
						yLineXStartCoord, yPoint[i], paint);
				break;
			case 5:
				canvas.drawLine(yLineXStartCoord, yPoint[i - 1],
						yLineXStartCoord, yPoint[i], paint);
				break;
			default:
				break;
			}
			paint.setColor(Color.BLACK);
			paint.setTextSize(14);
			paint.setTypeface(font);
			paint.setTextAlign(Align.RIGHT);
			canvas.drawText(yScaleLeftLabel[i], yLineXStartCoord - 6,
					yPoint[i], paint);
			paint.setTextAlign(Align.LEFT);
			if (i == 0) {
				canvas.drawText(yScaleRightLabel[i], yLineXStartCoord + 6,
						(xLineYStartCoord + yPoint[i]) / 2, paint);
			} else {
				canvas.drawText(yScaleRightLabel[i], yLineXStartCoord + 6,
						(yPoint[i - 1] + yPoint[i]) / 2, paint);
			}
		}

		// 绘制折现
		float stepX = (float) xLineLength / 25.0f;
		float lowStepY = (float) (yLineLength - (yStep * 2)) / 200.0f;
		float middleStepY = (float) yStep / 100.0f;
		float highStepY = (float) yStep / 200.0f;
		for (int z = 0; z < lineInfos.size(); z++) {
			LineInfo lineInfo = lineInfos.get(z);

			int lastStepX = -1;
			int lastStepY = -1;
			for (int j = 0; j < lineInfo.getPoints().length; j++) {
				// 确定点的位置
				int pointX = xLineXStartCoord + (int) (j * stepX);
				int pointY = 0;
				if (lineInfo.getPoints()[j] >= 500) {
					pointY = yLineYEndCoord;
				} else if (lineInfo.getPoints()[j] >= 200) {
					if (lineInfo.getPoints()[j] > 300) {
						int high = lineInfo.getPoints()[j] - 300;
						pointY += (high * highStepY);
						pointY += (100 * middleStepY);
					} else {
						int middle = lineInfo.getPoints()[j] - 200;
						pointY += (middle * middleStepY);
					}
					pointY += (200 * lowStepY);
					pointY = yLineYStartCoord - pointY;
				} else {
					pointY = yLineYStartCoord
							- (int) (lowStepY * lineInfo.getPoints()[j]);
				}

				// 画点
				paint.setColor(lineInfo.getPointColor());
				canvas.drawCircle(pointX, pointY, 3, paint);

				// 连线
				if (lastStepX != -1 && lastStepY != -1) {
					paint.setColor(lineInfo.getLineColor());
					paint.setStrokeWidth(1.5f);
					canvas.drawLine(lastStepX, lastStepY, pointX, pointY, paint);
				}

				// 记录
				lastStepX = pointX;
				lastStepY = pointY;
			}

			// 在图表画折现标识
			int markY = this.getBottom() - 40;
			int markX = xLineXStartCoord
					+ (xLineLength / (lineInfos.size() + 1)) * (z + 1) - 30;

			paint.setColor(lineInfo.getPointColor());
			canvas.drawCircle(markX, markY, 3, paint);
			paint.setColor(lineInfo.getLineColor());
			paint.setStrokeWidth(1.5f);
			canvas.drawLine(markX - 10, markY, markX + 10, markY, paint);
			paint.setColor(Color.BLACK);
			paint.setTextAlign(Align.LEFT);
			canvas.drawText(lineInfo.getName(), markX + 10, markY + 5, paint);
		}

	}

	public CharInfo getCharInfo() {
		return charInfo;
	}

	public void setCharInfo(CharInfo charInfo) {
		this.charInfo = charInfo;
	}

	public List<LineInfo> getLineInfos() {
		return lineInfos;
	}

	public void setLineInfos(List<LineInfo> lineInfos) {
		this.lineInfos = lineInfos;
	}

}
