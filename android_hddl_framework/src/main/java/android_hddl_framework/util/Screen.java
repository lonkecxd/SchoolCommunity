package android_hddl_framework.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;

/**
 * @author Derrick
 * @date 2014-5-26 下午5:14:16
 */
public class Screen {

	static final Screen SCREEN = new Screen();

	public int widthPixels;// 屏幕宽
	public int heightPixels;// 屏幕高
	public static int barHeight;// 状态栏高度
	public float density;
	public float scaledDensity;
	public int densityDpi;
	public float xdpi;
	public float ydpi;

	private Screen() {

	}

	public static void initScreen(Context context) {
		DisplayMetrics display = context.getResources().getDisplayMetrics();
		SCREEN.widthPixels = display.widthPixels;
		SCREEN.heightPixels = display.heightPixels;
		SCREEN.density = display.density;  			 //屏幕密度（0.75 / 1.0 / 1.5）
		SCREEN.scaledDensity = display.scaledDensity;//同display.density
		SCREEN.densityDpi = display.densityDpi;      // 屏幕密度DPI（120 / 160 / 240）
		SCREEN.xdpi = display.xdpi;
		SCREEN.ydpi = display.ydpi;
	}

	 /**
     * 获取状态栏高度
     */
    public static int getStatusHeight(Activity activity){
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        barHeight = rect.top;
        if (0 == barHeight){
            try {
            	Class<?> localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                barHeight = activity.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return barHeight;
    }
    
	public static void setBarHeight(int barHeight) {
		getInstance().barHeight = barHeight;
	}

	public static Screen getInstance() {
		return SCREEN;
	}
}
