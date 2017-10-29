package com.abhishek.character_scanner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.abhishek.character_scanner.R;

class FX extends Thread {
	private int i;
	private int prespos = 0;
	private int sH;
	private int scan;
	public boolean scanStat;
	public int spos;

	FX(int paramInt1, int paramInt2, int paramInt3) {
		spos = paramInt1;
		scan = paramInt2;
		sH = paramInt3;
	}

	public void run() {
		prespos = spos;
		scanStat = false;
		i = 0;
		while (!scanStat) {
			spos++;
			if (spos >= sH) {
				spos = prespos;
				i++;
			}
			if (i == scan) {
				scanStat = true;
			}
			try {
				Thread.sleep(1000 / sH);
			} catch (Exception e) {
			}
		}
	}
}

@SuppressLint("NewApi")
public class ScannerView extends View {
	private Bitmap Beem;
	private Bitmap RBeem;
	private Paint Bpaint;
	private Paint Gpaint;
	private Bitmap RGreen;
	private Bitmap Green;
	private Bitmap Red;
	private Bitmap RRed;
	private Paint Wpaint;
	private boolean analysis_done = false;
	private String charecter;
	private List<String> chars = new ArrayList<String>();
	private int charsel = 0;
	private boolean done_flag = false;
	private FX fx;
	private int sci = 0;
	private int i;
	private int rad = 5;
	private int win = 0;
	private int win2 = 0;
	private int preran = -1;
	private int ran = 0;
	private String place;
	private boolean scanned = false;
	private boolean scanning = false;
	private int scanpos = 0;
	private int scans = 3;
	private Context scontext;
	private int screenH;
	private int screenW;
	private boolean toasted = false;
	private Thread fx3;
	private int menuk = 0;
	private int mr = 20;
	private int sm = 10;
	private int yGen = 0;

	public ScannerView(Context context) {
		super(context);
		scontext = context;
		Gpaint = new Paint();
		Gpaint.setColor(Color.GREEN);
		Gpaint.setStrokeWidth(5);
		Bpaint = new Paint();
		Bpaint.setColor(Color.BLUE);
		Bpaint.setStrokeWidth(2);
		Wpaint = new Paint();
		Wpaint.setAntiAlias(true);
		Wpaint.setColor(Color.WHITE);
		Wpaint.setStyle(Paint.Style.STROKE);
		Wpaint.setTextAlign(Paint.Align.LEFT);
		try {
			AssetManager assetManager = context.getAssets();

			InputStream inputStream = assetManager.open("beem.jpg");
			Beem = BitmapFactory.decodeStream(inputStream);
			RBeem = Beem;

			inputStream = assetManager.open("green.jpg");
			Green = BitmapFactory.decodeStream(inputStream);
			RGreen = Green;

			inputStream = assetManager.open("red.jpg");
			Red = BitmapFactory.decodeStream(inputStream);
			RRed = Red;

			inputStream.close();
		} catch (Exception e) {
		}
		Wpaint.setTextSize(15 * scontext.getResources().getDisplayMetrics().density);
		genderShow();
	}

	protected void genderShow() {
		final Dialog gendial = new Dialog(scontext, R.style.TransparentDialog);
		gendial.setContentView(R.layout.gender);
		Button MBut = (Button) gendial.findViewById(R.id.Male_B);
		Button FBut = (Button) gendial.findViewById(R.id.Fe_B);
		MBut.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				yGen = 0;
				Toast.makeText(scontext, "You are a Male!", Toast.LENGTH_SHORT)
						.show();
				gendial.dismiss();
				setCharecters();
				invalidate();
			}
		});
		FBut.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				yGen = 1;
				Toast.makeText(scontext, "You are a Female!",
						Toast.LENGTH_SHORT).show();
				setCharecters();
				gendial.dismiss();
				invalidate();
			}
		});
		gendial.show();
	}

	protected void setCharecters() {
		try {
			if (!chars.isEmpty())
				chars.clear();
			if (yGen == 0) {
				chars.add("Shy but talks too much to close friends");
				chars.add("Likes the company of girls; reluctant to say");
				chars.add("Like others doing your work");
				chars.add("Gadgets are a weakness to you");
				chars.add("You are always in a hurry");
				chars.add("Laughing at others gives you pleasure");
				chars.add("You are a laughing stock of others");
				chars.add("Life is a challenge to you");
				chars.add("Good food always fills your eyes");
				chars.add("You never say Die");
				chars.add("Hiding too much things is not good for your heart");
				chars.add("You are childish in many ways");
				chars.add("Always thirsty for money");
				chars.add("You never believe fools");
				chars.add("You work hard but still get hard losses");
				chars.add("Cheating is an art for you");
				chars.add("You never get satisfied");
				chars.add("You like Cheap publicity");
				chars.add("You respect Liquor");
				chars.add("You are a different person under the influence of liquor");
				chars.add("Peeping into other's privacy is an interesting hobby for you");
				chars.add("You enjoy insulting others");
				chars.add("You find it easy to find faults of others");
				chars.add("Pessimism is your life mantra");
				chars.add("Your are a greedy and sadist person");
				chars.add("Handling girls is an art for you");
				chars.add("You find yourself alone in real situations");
				chars.add("You are tough like a rock");
				chars.add("Escapism is a saviour for you");
				chars.add("Inconsistency is the code of your mind");
			} else {
				chars.add("Shy but talks too much to Best friends");
				chars.add("Likes the company of boys; reluctant to say");
				chars.add("Like others doing your work");
				chars.add("Gadgets maybe a weakness to you");
				chars.add("You are always in a hurry");
				chars.add("Laughing at others gives you pleasure");
				chars.add("You are a laughing stock of others");
				chars.add("Life is a challenge to you");
				chars.add("Good food always fills your eyes");
				chars.add("You are occasionally hyperactive");
				chars.add("Hiding too much things is not good for you");
				chars.add("You are childish in many ways");
				chars.add("You love shopping");
				chars.add("You love dancing");
				chars.add("You work hard but still get hard losses");
				chars.add("Cheating is an art for you");
				chars.add("You never get satisfied");
				chars.add("You like Cheap publicity");
				chars.add("You respect teachers");
				chars.add("You are a different person in presence of you friends");
				chars.add("Peeping into other's privacy is an interesting hobby for you");
				chars.add("You enjoy insulting others");
				chars.add("You find it easy to find faults of others");
				chars.add("Pessimism is your life mantra");
				chars.add("Your are a greedy person");
				chars.add("Handling boys is an art for you");
				chars.add("You find yourself alone in real situations");
				chars.add("You are tough like a rock");
				chars.add("Escapism is a saviour for you");
				chars.add("Inconsistency is the code of your mind");
			}
			return;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	protected void onDraw(Canvas g) {
		if (!done_flag) {
			if (menuk == 0)
				g.drawCircle(screenW - sm - mr, mr + sm, mr, Bpaint);
			else
				g.drawCircle(screenW - sm - mr, mr + sm, mr, Gpaint);
			if (scanning) {
				g.drawText(place, 10, 10 + Wpaint.getTextSize(), Wpaint);

				if (yGen == 0)
					g.drawText("You are a Male!", 10,
							screenH - (10 + Wpaint.getTextSize()), Wpaint);
				else
					g.drawText("You are a Female!", 10,
							screenH - (10 + Wpaint.getTextSize()), Wpaint);

				if (sci > 30) {
					sci = 0;
				}
				sci++;
				place = "Scanning";
				if (!(sci <= 10)) {
					for (int y = 1; y <= sci / 10; y++) {
						place += ".";
					}
				}
				g.drawBitmap(RRed, (screenW - RRed.getWidth()) / 2,
						(screenH - RRed.getHeight()) / 2, null);
				scanpos = fx.spos;
				if (!fx.scanStat) {
					g.drawBitmap(RBeem, (screenW - RBeem.getWidth()) / 2,
							scanpos, null);
				}
				scanned = fx.scanStat;
				if ((scanned) && (!toasted)) {
					Toast.makeText(scontext,
							"Scanning Complete please raise finger! ",
							Toast.LENGTH_SHORT).show();
					place = "Scanning Complete please raise finger! ";
					toasted = true;
					analasis();
				}
				invalidate();
			} else {
				g.drawText(place, 10, 10 + Wpaint.getTextSize(), Wpaint);
				g.drawText("(Press the Dot for Gender)", 10,
						2 * (10 + Wpaint.getTextSize()), Wpaint);

				if (yGen == 0)
					g.drawText("You are a Male!", 10,
							screenH - (10 + Wpaint.getTextSize()), Wpaint);
				else
					g.drawText("You are a Female!", 10,
							screenH - (10 + Wpaint.getTextSize()), Wpaint);

				g.drawBitmap(RGreen, (screenW - RGreen.getWidth()) / 2,
						(screenH - RGreen.getHeight()) / 2, null);

				place = "Place your finger:-";
				sci = 0;
				scanpos = (-20 + (screenH - RRed.getHeight()) / 2);
				invalidate();
			}
		} else {
			if (i < 100) {
				g.drawLine(0, -90 + (screenH / 2) - rad, screenW, -90
						+ (screenH / 2) - rad, Bpaint);

				for (int y = 0; y < 4; y++) {
					if (i % 4 == 0) {
						waver();
					}

					if (win == 0) {
						g.drawCircle(screenW / 4, (-50 + screenH / 2), rad,
								Gpaint);
					} else if (win == 1) {
						g.drawCircle(screenW / 2, (-50 + screenH / 2), rad,
								Gpaint);
					} else if (win == 2) {
						g.drawCircle((screenW / 2) + (screenW / 4),
								(-50 + screenH / 2), rad, Gpaint);
					}
				}

				if (i <= 25) {
					g.drawText("Analysing : " + i + "%", 50,
							(screenH - Wpaint.getTextSize()) / 2, Wpaint);
				} else if (i <= 50) {
					g.drawText("Processing : " + i + "%", 50,
							(screenH - Wpaint.getTextSize()) / 2, Wpaint);
				} else if (i <= 75) {
					g.drawText("Algorithemic decoding : " + i + "%", 50,
							(screenH - Wpaint.getTextSize()) / 2, Wpaint);
				} else if (i <= 100) {
					g.drawText("Generating result : " + i + "%", 50,
							(screenH - Wpaint.getTextSize()) / 2, Wpaint);
				}

				g.drawLine(0,
						40 + ((screenH - Wpaint.getTextSize()) / 2) + rad,
						screenW, 40 + ((screenH - Wpaint.getTextSize()) / 2)
								+ rad, Bpaint);
				invalidate();
				analysis_done = false;
			} else {
				g.drawLine(0, (screenH - Wpaint.getTextSize()) / 2 - 40,
						screenW, (screenH - Wpaint.getTextSize()) / 2 - 40,
						Bpaint);
				g.drawText("Analysing : " + (i) + "%", 50,
						(screenH - Wpaint.getTextSize()) / 2, Wpaint);
				g.drawText("Touch the screen!", 50,
						20 + (screenH - Wpaint.getTextSize()) / 2, Wpaint);
				g.drawLine(0, 60 + (screenH - Wpaint.getTextSize()) / 2,
						screenW, 60 + (screenH - Wpaint.getTextSize()) / 2,
						Bpaint);
				analysis_done = true;
				invalidate();
				char_show();
			}
		}
		g.drawLine(0, 3, screenW, 3, Bpaint);
		g.drawLine(0, 3, 0, screenH, Bpaint);
		g.drawLine(screenW, screenH, 0, screenH, Bpaint);
		g.drawLine(screenW, screenH, screenW, 3, Bpaint);
	}

	public void waver() {
		if (win2 != i) {
			win++;
			if (win > 2) {
				win = 0;
			}
			win2 = i;
		}
	}

	public void analasis() {
		scanning = false;
		fx = null;
		fx3 = new Thread() {
			public void run() {
				i = 0;
				while (i <= 100) {
					i++;
					try {
						Thread.sleep(60);
					} catch (Exception e) {
					}
				}
			}
		};

		ran = new Random().nextInt(chars.size());
		while (ran == preran) {
			ran = new Random().nextInt(chars.size());
		}

		charsel = ran;
		preran = ran;
		charecter = ((String) chars.get(charsel));
		i = 0;
		fx3.start();
		done_flag = true;
		fx = null;
		invalidate();
	}

	public void char_show() {
		if (analysis_done) {
			showCharecter(charecter + "!");
			analysis_done = false;
			done_flag = false;
			scanned = false;
			scanning = false;
			toasted = false;
			fx = null;
			win = 0;
			win2 = 0;
		}
		invalidate();
	}

	public boolean onTouchEvent(MotionEvent event) {
		int eventaction = event.getAction();
		int X = (int) event.getX();
		int Y = (int) event.getY();

		switch (eventaction) {

		case MotionEvent.ACTION_DOWN:
			if ((X >= ((screenW - mr + sm) - mr))
					&& (X <= ((screenW - mr + sm) + mr))) {
				if ((Y >= ((mr + sm) - mr)) && (Y <= ((mr + sm) + mr))) {
					menuk = 1;
					((Activity) getContext()).openOptionsMenu();
				}
			}
			if (!scanned) {
				fx = new FX(scanpos, scans, (screenH - RRed.getHeight()) / 2
						+ RRed.getHeight());
				fx.start();
				if ((X > (screenW - RRed.getWidth()) / 2)
						&& (X < (screenW - RRed.getWidth()) / 2
								+ RRed.getWidth())
						&& (Y > (screenH - RRed.getHeight()) / 2)
						&& (Y < (screenH - RRed.getHeight()) / 2
								+ RRed.getHeight())) {
					scanning = true;
				}
			}
			invalidate();
			break;

		case MotionEvent.ACTION_MOVE:
			if (!((X > (screenW - RRed.getWidth()) / 2)
					&& (X < (screenW - RRed.getWidth()) / 2 + RRed.getWidth())
					&& (Y > (screenH - RRed.getHeight()) / 2) && (Y < (screenH - RRed
					.getHeight()) / 2 + RRed.getHeight()))) {
				if (!scanned) {
					scanning = false;
					fx = null;
				}
			}
			invalidate();
			break;

		case MotionEvent.ACTION_UP:
			if (menuk == 1) {
				menuk = 0;
			}
			if (!scanned) {
				scanning = false;
				fx = null;
			}
			invalidate();
			break;
		}
		invalidate();
		return true;
	}

	public void onSizeChanged(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4) {
		super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
		screenW = paramInt1;
		screenH = paramInt2;
		RRed = Bitmap.createScaledBitmap(Red, screenW / 2, screenH / 2, true);
		RGreen = Bitmap.createScaledBitmap(Green, screenW / 2, screenH / 2,
				true);
		RBeem = Bitmap
				.createScaledBitmap(Beem, screenW, Beem.getHeight(), true);
		place = "Place your finger:-";
		rad = ((screenH + screenW) / 60);
	}

	public void showCharecter(String chartex) {
		final Dialog chardial = new Dialog(scontext, R.style.TransparentDialog);
		chardial.setContentView(R.layout.charecter);
		final TextView chartext = (TextView) chardial
				.findViewById(R.id.charecter);
		chartext.setTextColor(Color.CYAN);
		chartext.setTextSize(25);
		Button okButton = (Button) chardial.findViewById(R.id.ok_b);
		chartext.setGravity(Gravity.CENTER);
		chartext.setText(chartex);
		okButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Toast.makeText(scontext, "Thankyou for using this app!",
						Toast.LENGTH_SHORT).show();
				chardial.dismiss();
				done_flag = false;
				analysis_done = false;
				fx3 = null;
				scanned = false;
				scanning = false;
				toasted = false;
				fx = null;
				place = "Place your finger:-";
				invalidate();
			}
		});
		chardial.show();
	}
}
