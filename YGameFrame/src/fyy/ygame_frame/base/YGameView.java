package fyy.ygame_frame.base;

import java.util.ArrayList;
import java.util.Map;

import fyy.ygame_frame.base.broadcast.YIBroadcast;
import fyy.ygame_frame.base.broadcast.YIReceiver;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
*<b>��Ϸ�߼�</b>
*
*<p>
*<b>����</b>��
*��Ϸ���<b><i>ʵ��</i></b>��Domain����ָ�κο��ӻ��Ķ���
*���ͼ�����顢������ܵȡ��κ�һ��<b><i>ʵ��</i></b>�������������ɣ�
*<ol>
*<li>�������ݣ�{@link YABaseDomainData}
*<li>�����߼���{@link YABaseDomainLogic}
*<li>������ͼ��{@link YABaseDomainView}
*</ol>
*�����������ά�����������е�<b>������ͼ</b>��
*
*<p>
*<b>��ϸ</b>����������ְ�����£�
<li>����<b>��Ϸ�߼�</b>{@link YAGameLogic}������<b>��ͼ��Ϣ</b>{@link YDrawInformation}�б�
*���ɸ���Ӧ��<b>������ͼ</b>���ƣ������һ֡����Ļ��ƣ�
*<li>����androidϵͳ�Ļص������������и�������Ӧ�����̣����<b>��Ϸ�߼�</b>�Ŀ������ս�
* ����ͣ������<b>������ͼ</b>���ա�����λͼ�ȡ�<br>
*<b>���п�ͼ���£�</b>
*<p>
*<img src="../../../YGameFrame.jpg" height="380" width="550"/>
*</p>
*
*<p>
*<b>ע</b>��
*<li>һ��<b>������ͼ</b>��ʵ��һ��Ҫͨ��{@link #addDomainView(YABaseDomainView...)}������ӵ��������С�
*<li>������ʵ����<b>�㲥�ӿ�</b>{@link YIBroadcast}<br>
*
*@author FeiYiyun
*
*/
public class YGameView extends SurfaceView implements SurfaceHolder.Callback, YIBroadcast
{
	/** ��־��ǩ */
	private static final String strTag = "YAGameView";
	/** ��Ϸ��ͼ�����������ͼ�б� */
	/**<b>������ͼ�б�</b>������������<b>������ͼ</b>{@link YABaseDomainView}�б�*/
	// ��������˳��Ľṹ������ͼ����ܱ��ڸ�
	private ArrayList<YABaseDomainView<?>> domainViews = new ArrayList<YABaseDomainView<?>>();
	/**<b>��Ϸ��ͼ������</b>*/
	private SurfaceHolder surfaceHolder;
	/** ��ģ���к��������̣߳����߳̽���<b>��Ϸ��ͼ</b>{@link YAGameLogic}�����Ļ�ͼ��Ϣ������ͼ */
	/**<b>��ͼ�߳�</b>������<b>��Ϸ�߼�</b>{@link YAGameLogic}�����Ļ�ͼ��Ϣ��
	 *������ɸ�����<b>������ͼ</b>{@link YABaseDomainView}���л���*/
	private YDrawThread drawThread;
	/**<b>������Ϣ����</b>����<b>��Ϸ��ͼ</b>{@link YGameView}�����������Ϣ*/
	protected Paint paintTest;
	/**<b>�����߼�</b>����֮�������<b>��Ϸ�߼�</b>{@link YAGameLogic}*/
	private YAGameLogic gameLogic;

	/**<b>��ͼ���ֲ���</b>���ɡ���ͼ��������ɺ󣬽�һ��ȷ���Ĳ��ֲ�������������
	*<b>������ͼ</b>{@link YABaseDomainView}����Ļ����ʱ�Ĳο���<br>
	*<b>��ϸ</b>����ν����ͼ����Ϊ<b>������ͼ</b>�����࣬��ͨ��Ӧ���ǵ�һ����ӵ�<b>��Ϸ��ͼ</b>
	*{@link YGameView}�е�<b>������ͼ</b>�������������ɺ����б�Ҫ�Ļ�����Ӧ�ø������
	*�ò���������ò���Ϊ�ա�<br>
	*<b>��</b>��*/
	private int[] iMapLayoutParams;

	/**
	*@param context ������
	*@param attrs ����
	*/
	public YGameView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init(context);
		Log.i(strTag, "��Ϸ��ͼ�������");
	}

	/**��ʼ��
	*@param context ������
	*@throws ExceptionInInitializerError ��ʼ������
	*/
	private void init(Context context) throws ExceptionInInitializerError
	{// ������Ϸ�߼�����ȡ�����ߡ���ӻص����½���ͼ�̡߳���ʼ������
		final String strGameViewTag = (String) getTag();
		if (null == strGameViewTag)
		{
			Log.e(strTag, "��Ϸ��ͼ��tag����Ϊ�գ�Ӧ������Ϊ������Ϸ�߼�������");
			throw new ExceptionInInitializerError("��Ϸ��ͼ��ʼ���쳣");
		}

		final String strLogicName = "fyy.ygame_frame.base." + strGameViewTag;
		try
		{
			Class<?> clssLogic = Class.forName(strLogicName);
			gameLogic = (YAGameLogic) clssLogic.newInstance();
		} catch (Throwable e)
		{
			Log.e(strTag, "û���ҵ���GameView��ص���Ϸ�߼���������"
					+ "GameView��tag�������Ϸ�߼���ƴд����ֻд�������ɣ���Ҫ��Ӱ�����");
			throw new ExceptionInInitializerError("��Ϸ��ͼ��ʼ���쳣");
		}

		if (context instanceof YGameActivity)
			gameLogic.setGameActivity((YGameActivity) context);
		else
		{
			Log.e(strTag, "��Ϸ��Activity��Ҫ�̳���YGameActivity");
			throw new ExceptionInInitializerError("��Ϸ��Activity���ʹ���");
		}

		// ��ȡSurfaceHolder
		surfaceHolder = getHolder();
		// ��ӻص��ӿ�
		surfaceHolder.addCallback(this);
		// ������Ϸ��ͼ���ǻ�ȡ����
		setFocusable(true);

		// �½���ͼ�߳�
		drawThread = new YDrawThread();

		// ���Ի���
		paintTest = new Paint();
		paintTest.setTextSize(30);
		paintTest.setColor(0xff000000);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
	{

	}

	public void surfaceCreated(SurfaceHolder holder)
	{// ֪ͨ����������ͼ����λͼ��������ͼ�̡߳�������Ϸ�߼�
		// 1.
		int iWidth = getWidth();
		int iHeight = getHeight();
		Resources res = getResources();
		for (YABaseDomainView<?> domainView : domainViews)
			domainView.onLoadBitmaps(res, iWidth, iHeight, iMapLayoutParams);
		// 2.
		drawThread.start();
		try
		{
			Thread.sleep(100);
		} catch (InterruptedException e)
		{
			Log.e(strTag, "surfaceCreated�����߳���ͣ�쳣");
			e.printStackTrace();
		}
		// 3.
		gameLogic.start();
	}

	/**
	 * ��surface�����٣� <li>�˳���Ϸ�߼� <li>������Ϸ��ͼ�̵߳���Ϣѭ����������ͼ�߳� <li>֪ͨ����������ͼ�ӿڻ���λͼ
	 */
	public void surfaceDestroyed(SurfaceHolder holder)
	{// �˳���Ϸ�߼����˳���ͼ�̡߳�֪ͨ������ͼ����λͼ
		// 1.
		gameLogic.end();
		// 2.
		drawThread.lpr.quit();
		while (drawThread.isAlive())
		{
			try
			{
				drawThread.join();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

		}

		// 3.
		for (YABaseDomainView<?> domainView : domainViews)
			domainView.onRecycleBitmaps();
	}

	/**����<b>��ͼ��Ϣ</b>{@link YDrawInformation}������<b>������ͼ</b>{@link YABaseDomainView}��
	*ˢ��һ֡��ͼ��
	*@param mapDrawInfos <b>��ͼ��Ϣ</b>�б�
	*@param iFps ֡���ʣ����ڲ����ã�Ŀǰû��ʹ�ã�
	*/
	@SuppressLint("WrongCall")
	private final void refresh(Map<Integer, YDrawInformation> mapDrawInfos, int iFps)
	{
		Canvas canvas = null;
		try
		{
			// 1.��������
			canvas = surfaceHolder.lockCanvas();
			if (null != canvas)
			{// 2.�����ɹ�����ͼ
				canvas.drawColor(0xffffffff);
				for (YABaseDomainView<?> domainView : domainViews)
					// ???bug
					// ���������ͼ�������߼����ݲ�ͬʱ�ô���ë����
					domainView.onDraw(canvas, mapDrawInfos.get(domainView.iID));
				canvas.drawText("" + iFps, 20, 30, paintTest);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			Log.e(strTag, "GameView.refresh(...)�����쳣");
		} finally
		{
			// 3.������ζ�Ҫ�ύ�������ǿ�ʱ��
			if (null != canvas)
				surfaceHolder.unlockCanvasAndPost(canvas);
		}
	}

	/**���<b>������ͼ</b>{@link YABaseDomainView}��<br><b>ע</b>�����ջ�ͼ˳����ӣ������ͼ
	*ʱ���ڸǣ�
	*@param  domainViews ��ӵ�<b>������ͼ</b>���ɶ��
	*/
	public final void addDomainView(YABaseDomainView<?>... domainViews)
	{
		for (YABaseDomainView<?> domainView : domainViews)
		{
			this.domainViews.add(domainView);
			domainView.broadcastView = this;
		}
	}

	/**�Ƴ�<b>������ͼ</b>{@link YABaseDomainView}��
	*@param domainViews ��Ҫ�Ƴ���<b>������ͼ</b>���ɶ��
	*/
	public final void removeDomainView(YABaseDomainView<?>... domainViews)
	{
		for (YABaseDomainView<?> domainView : domainViews)
			this.domainViews.remove(domainView);
	}

	/**�����<b>��Ϸ��ͼ</b>{@link YGameView}������<b>������Ϸ�߼�</b>
	*@return <b>������Ϸ�߼�</b>
	*/
	@SuppressWarnings("unchecked")
	public final <GL extends YAGameLogic> GL getGameLogic()
	{
		return (GL) gameLogic;
	}

	public void send(int iMsgKey, Object objDetailMsg, YIReceiver receiverSend)
	{
		if (YGameEnvironment.BroadcastMsgKey.MSG_MAP_VIEW_LAYOUTED == iMsgKey)
			iMapLayoutParams = (int[]) objDetailMsg;
		for (YABaseDomainView<?> baseDomainView : domainViews)
			if (baseDomainView != receiverSend)
				baseDomainView.onReceiveBroadcastMsg(iMsgKey, objDetailMsg);
	}

	private static class YDrawHandler extends Handler
	{
		private YGameView gameView;

		private YDrawHandler(YGameView gameView)
		{
			this.gameView = gameView;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(Message msg)
		{
			gameView.refresh((Map<Integer, YDrawInformation>) (msg.obj), 0);
		}
	}

	/**
	*<b>��Ϣ�߳�</b>
	*
	*<p>
	*<b>����</b>��
	*ˢ����ͼ��
	*
	*<p>
	*<b>��ϸ</b>��
	*����<b>��Ϸ�߼�</b>{@link YAGameLogic}�����Ļ�ͼ��Ϣ����msg.obj��ȡ��<b>��ͼ��Ϣ</b>
	*�б����������<b>������ͼ</b>{@link YABaseDomainView}�ַ�<b>��ͼ��Ϣ</b>����ɻ��ơ�
	*
	*@author FeiYiyun
	*
	*/
	private final class YDrawThread extends Thread
	{
		/**<b>��ͼ�̵߳���Ϣ���</b>������<b>��Ϸ��ͼ</b>����ʱ�˳����̡߳�*/
		private Looper lpr;
		private static final String strDrawThreadName = "��ͼ�߳�";

		private YDrawThread()
		{
			super();
			setName(strDrawThreadName);
		}

		@Override
		public void run()
		{
			// ������Ϣ����
			Looper.prepare();
			// ��ȡ��Ϣѭ�����ã��Ա��˳�
			lpr = Looper.myLooper();
			// ע���½�Handlerһ��Ҫ��Looper.prepare()��
			gameLogic.setGameViewHandler(new YDrawHandler(YGameView.this));
			// ������Ϣѭ��
			Looper.loop();
		}
	}
}
