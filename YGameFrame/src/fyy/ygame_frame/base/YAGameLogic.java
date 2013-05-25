package fyy.ygame_frame.base;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import fyy.ygame_frame.base.broadcast.YIBroadcast;
import fyy.ygame_frame.base.broadcast.YIReceiver;
import fyy.ygame_frame.extra.YITask;
import fyy.ygame_frame.extra.YRequest;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * <b>��Ϸ�߼�</b>
 * 
 * <p>
 * <b>����</b>�� ��Ϸ���<b><i>ʵ��</i></b>��Domain����ָ�κο��ӻ��Ķ���
 * ���ͼ�����顢������ܵȡ��κ�һ��<b><i>ʵ��</i></b>�������������ɣ�
 * <ol>
 * <li>�������ݣ�{@link YABaseDomainData}
 * <li>�����߼���{@link YABaseDomainLogic}
 * <li>������ͼ��{@link YABaseDomainView}
 * </ol>
 * �����������ά�����������е�<b>�����߼�</b>��
 * 
 * <p>
 * <b>��ϸ</b>����������ְ�����£�
 * <li>ÿ�����ڵ�����Ҫ��<b>�����߼�</b>�ύ<b>����</b>{@link YITask}
 * ������֮���õ�<b>��ͼ��Ϣ</b> {@link YDrawInformation} ���������ǲ���һ֡����Ϣ��
 * ͳһ�ύ��<b>��Ϸ��ͼ</b> {@link YGameView} �����һ֡����Ļ��ƣ�
 * <li>ÿ�����ڵ�����֪ͨ<b>�����߼�</b>����<b>����</b>��
 * <li>����ʱ�أ��������ͨ������������<b>����</b>����Ϸ���������<b>����</b>
 * {@link YRequest}�� ת������Ӧ��<b>�����߼�</b>����<br>
 * <b>���п�ͼ���£�</b>
 * <p>
 * <img src="../../../YGameFrame.jpg" height="380"
 * width="550"/>
 * </p>
 * 
 * <p>
 * <b>ע</b>��
 * <li>һ��<b>�����߼�</b>��ʵ��һ��Ҫͨ��
 * {@link #addDomainLogic(YABaseDomainLogic...)} ������ӵ��������С�
 * <li>������ʵ����<b>�㲥�ӿ�</b>{@link YIBroadcast}<br>
 * 
 * @author FeiYiyun
 * 
 */
@SuppressLint(
{ "UseSparseArrays" })
public abstract class YAGameLogic implements YIBroadcast
{
	private static final String strUpdateThreadName = "�����߳�";
	private final String strTag = "YAGameLogic";
	/*
	 * //��ϣ��������Ŀǰ��ʹ�� private HashMap<Integer,
	 * YABaseDomainLogic<?, ?>> domainLogics = new
	 * HashMap<Integer, YABaseDomainLogic<?, ?>>(); /**
	 * ���������������߼����б�<br> ѡ��LinkedHashMap������Щ���ǣ�
	 * <li>���Ը��ݼ�ֵ��ȡ���ã������ҵ��� <li>��˳�� ����ͼ��Ϣ�б�����Ϸ��ͼ����
	 * </br> <b>???��ϣ����ɡ���Ҫ����Ϸ��ͼҲ���ù�ϣ��洢������ͼ����֪Ч�����</b>
	 */
	/**
	 * <b>�����߼��б�</b>���������<b>�����߼�</b>
	 * {@link YABaseDomainLogic}�洢�ڸ��б��У� ����<b>��Ϣ�߳�</b>
	 * {@link #msg_thrd}��<b>�����߳�</b>
	 * {@link #tmrUpdateLogic} ����Ҫ�Ը��б���в������ʲ�����ͬ����ϣ��
	 */
	private Map<Integer, YABaseDomainLogic<?>> domainLogics = new ConcurrentHashMap<Integer, YABaseDomainLogic<?>>();
	/** <b>��������</b>��������Ϸ�߼������ڣ���λΪ���룬Ĭ��Ϊ30���롣 */
	private int iUpdatePeriod = 30;
	/** <b>��Ϸ�߼�������ʶ</b>����Ϸ�߼��Ƿ������ı�ʶ��Ĭ�ϼ١� */
	private boolean bStart;
	/**
	 * <b>��Ϸ�</b>������<b>��Ϸ��ͼ</b>{@link YGameView}
	 * ��Activity��
	 */
	protected YGameActivity gm_actvty;

	/** <b>��Ϣ�߳�</b>�����б�����������߳�֮һ�����̸߳������������Ϣ���������� */
	private YMsgThread msg_thrd;
	/** <b>�����߳�</b>�����б�����������߳�֮һ�����̸߳���ʱ������Ϸ�߼��� */
	private Timer tmrUpdateLogic;
	/** <b>��������</b>�����ж�ʱ���µ����� */
	private TimerTask tmrTask;

	/**
	 * <b>��Ϸ��ͼ��Ϣ���</b>����<b>��Ϸ��ͼ</b>{@link YGameView}ͨ��
	 * ����Ϣ�����һ��أ�ÿһ֡�����<b>��ͼ��Ϣ</b>
	 * {@link YDrawInformation}�б�����װ��msg.obj��
	 * ͨ���þ�����ݸ�<b>��Ϸ��ͼ</b>��
	 */
	private Handler hndlrGameView;

	/**
	 * <b>��Ϸ�߼���Ϣ���</b>����Ҳ��������ĺ�<b>�����߼�</b>
	 * {@link YABaseDomainLogic}
	 * ��ʱ���²�����<b>����</b>ͨ���þ�����ύ��
	 */
	private Handler handlerGameLogic;

	/**
	 * ����������յ���Ҳ��������Ļ���Ϸ�ڲ�����<b>����</b>{@link YRequest}ʱ��
	 * �ú������ᱻ�ص�������Ӧ����д�����������ò�ͬ�Ĳ���ȥ������Щ<b>����</b>��
	 * 
	 * @param rqstUIorGame
	 *                ��������Ҳ��������Ļ���Ϸ�ڲ�������<b>����</b>
	 */
	protected abstract void onReceiveRequest(YRequest rqstUIorGame);

	/**
	 * ����Ӧ��д������������ָ����<b>�����߼�</b>
	 * {@link YABaseDomainLogic} �ύ��Ӧ��<b>����</b>
	 * {@link YITask}�б�
	 * 
	 * @param dmn_lgcKey
	 *                ָ����<b>�����߼�</b>
	 * @return <b>����</b>�б�
	 */
	protected abstract YITask[] onSubmitCurrentTasks(YABaseDomainLogic<?> dmn_lgcKey);

	/**
	 * ����<b>�����߼�</b>{@link YABaseDomainLogic}
	 * �����ʱ���ú��������á�
	 * ����б�Ҫ�Ļ�������Ӧ��ͨ����д������Ϊ����ӵ�<b>�����߼�</b>����Ӧ�ĳ�ʼ��������
	 * 
	 * @param dmn_lgcKey
	 *                ������<b>�����߼�</b>
	 */
	// �������������ǻ��������Ŀ��ǣ���������Ķ�����Ҫһ����������
	// ��ô��ܿ��������Ǳ����ӽ���ʱͳһ��Ϊ�����������������󣬲�ͳһ����������Ҫ
	// ���Ƕ��Թ���
	protected abstract void onAddDomainLogic(YABaseDomainLogic<?> dmn_lgcKey);

	/**
	 * ����<b>�����߼�</b>{@link YABaseDomainLogic}
	 * ���Ƴ�ʱ���ú��������á�����б�Ҫ�Ļ��� ����Ӧ��ͨ����д����������Ӧ���ƺ��������ڴ���յȡ���
	 * 
	 * @param dmn_lgcKey
	 *                ���Ƴ���<b>�����߼�</b>
	 */
	protected abstract void onRemoveDomainLogic(YABaseDomainLogic<?> dmn_lgcKey);

	YAGameLogic()
	{

	}

	/**
	 * ����<b>��Ϸ��ͼ</b>{@link YGameView}����Ϣ���
	 * 
	 * @param hndlrGameView
	 *                <b>��Ϸ��ͼ</b>��Ϣ���
	 */
	final void setGameViewHandler(Handler hndlrGameView)
	{
		this.hndlrGameView = hndlrGameView;
	}

	/**
	 * ������Ϸ�߼���<li>�½�������<b>��Ϣ�߳�</b>
	 * {@link YAGameLogic#msg_thrd}�� <li>
	 * �½�������<b>�����߳�</b>{@link #tmrUpdateLogic}<br>
	 */
	synchronized final void start()
	{
		if (bStart)
		{
			Log.e(strTag, "��Ϸ�߼��Ѿ������������ٴ�������");
			throw new IllegalStateException("��Ϸ�߼��ظ�����");
		}
		bStart = true;
		msg_thrd = new YMsgThread();
		msg_thrd.start();
		try
		{
			Thread.sleep(100);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
			Log.e(strTag, "��Ϸ�߼�start()������ͣ�쳣");
		}

		tmrUpdateLogic = new Timer(strUpdateThreadName, true);// ��Ϊ�ػ��߳�
		tmrUpdateLogic.schedule(tmrTask = new TimerTask()
		{
			@Override
			public void run()
			{
				try
				{
					onUpdateLogic();
				} catch (Exception e)
				{
					e.printStackTrace();
					Log.e(strTag, "onUpdateLogic�����쳣");
				}
			}
		}, 0, iUpdatePeriod);

		Log.i(strTag, "��Ϸ�߼��������");
	}

	/**
	 * �˳���Ϸ�߼�������<b>��Ϣ�߳�</b>{@link #msg_thrd}��<b>�����߳�</b>
	 * {@link #tmrUpdateLogic}��
	 */
	synchronized final void end()
	{
		while (!tmrTask.cancel())
			;
		tmrUpdateLogic.purge();
		tmrUpdateLogic.cancel();
		tmrTask = null;
		tmrUpdateLogic = null;

		msg_thrd.lpr.quit();
		msg_thrd.lpr = null;
		while (msg_thrd.isAlive())
			try
			{
				msg_thrd.join();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
				Log.e(strTag, "��Ϸ�߼��̼߳����쳣");
			}
		bStart = false;
		Log.i(strTag, "��Ϸ�߼��˳����");
	}

	/**
	 * ���<b>�����߼�</b>{@link YABaseDomainLogic}
	 * �����������ͬID�Ķ���
	 * 
	 * @param domainLogics
	 *                ��ӵ�<b>�����߼�</b>���ɶ��
	 */
	public final void addDomainLogic(YABaseDomainLogic<?>... domainLogics)
	{
		for (YABaseDomainLogic<?> domainLogic : domainLogics)
		{
			if (this.domainLogics.containsKey(domainLogic.iID))
			{
				Log.e(strTag, "��Ϸ�߼����Ѿ�����IDΪ��" + domainLogic.iID + "�Ķ���"
						+ "�����ظ����ID��ͬ�Ķ���");
				throw new IllegalArgumentException("�ظ����ID��ͬ�Ķ���");
			}
			this.domainLogics.put(domainLogic.iID, domainLogic);
			domainLogic.handlerGameLogic = this.handlerGameLogic;
			domainLogic.broadcastLogic = this;
			onAddDomainLogic(domainLogic);
		}
	}

	/**
	 * �Ƴ�<b>�����߼�</b>{@link YABaseDomainLogic}��
	 * 
	 * @param domainLogics
	 *                ��Ҫ�Ƴ���<b>�����߼�</b>���ɶ��
	 */
	public final void removeDomainLogic(YABaseDomainLogic<?>... domainLogics)
	{
		for (YABaseDomainLogic<?> domainLogic : domainLogics)
		{
			this.domainLogics.remove(domainLogic.iID);
			onRemoveDomainLogic(domainLogic);
		}
	}

	/**
	 * ����<b>��Ϸ�߼�</b>{@link YAGameLogic}
	 * �������ڣ�����Ϊ��λ��Ĭ��Ϊ30����
	 * 
	 * @param iUpdatePeriod
	 *                ��������
	 */
	public final void setUpdatePeriod(int iUpdatePeriod)
	{
		if (bStart)
		{
			Log.w(strTag, "����Ϸ�߼����������������˸�������");
			throw new IllegalStateException("Ŀǰ��֧�ִ˲���");
		} else
			this.iUpdatePeriod = iUpdatePeriod;
	}

	/**
	 * ��ȡ�������ڣ��Ժ���Ϊ��λ��Ĭ��Ϊ30����
	 * 
	 * @return ��������
	 */
	public final int getUpdatePeriod()
	{
		return iUpdatePeriod;
	}

	/**
	 * �����߼���Ҫ�����<b>�����߼�</b>{@link YABaseDomainLogic}
	 * �ύ��������Ҫִ�е�<b>����</b> {@link YITask}
	 * ��ִ��֮���õ�<b>��ͼ��Ϣ</b>{@link YDrawInformation}����װ
	 * ��msg.obj�У�������<b>��Ϸ��ͼ</b>{@link YGameView}��
	 */
	private void onUpdateLogic()
	{
		Map<Integer, YDrawInformation> mapDrawInfos = new HashMap<Integer, YDrawInformation>();
		Iterable<YABaseDomainLogic<?>> iterable = domainLogics.values();
		for (YABaseDomainLogic<?> baseDomainLogic : iterable)
		{// ��ѯ�߼�����ӿ��б�����ֵ�������࣬�������ݼ�ֵȡ����Ӧ�������б�������õ������б��
			// ����ִ������ӿ�
			YITask[] tasks = onSubmitCurrentTasks(baseDomainLogic);
			if (null != tasks)
				for (YITask task : tasks)
					task.execute(baseDomainLogic.drawInfoForm,
							domainLogics);
			mapDrawInfos.put(baseDomainLogic.iID,
					baseDomainLogic.drawInfoForm.print());
		}
		Message msgView = Message.obtain();
		msgView.obj = mapDrawInfos;
		hndlrGameView.sendMessage(msgView);

		for (YABaseDomainLogic<?> dmn_lgc : iterable)
			// �ص�ÿ�������߼��ӿڣ����䷢������
			dmn_lgc.onSendRequests(domainLogics);
	}

	/**
	 * ���ù�����<b>��Ϸ�</b>{@link YGameActivity}
	 * 
	 * @param gm_actvty
	 *                ��Ϸ�
	 */
	final void setGameActivity(YGameActivity gm_actvty)
	{
		this.gm_actvty = gm_actvty;
	}

	public void send(int iMsgKey, Object objDetailMsg, YIReceiver receiverSend)
	{
		Iterable<YABaseDomainLogic<?>> iterable = domainLogics.values();
		for (YABaseDomainLogic<?> baseDomainLogic : iterable)
			if (baseDomainLogic != receiverSend)
				baseDomainLogic.onReceiveBroadcastMsg(iMsgKey, null);
	}

	/**
	 * <b>��Ϣ�߳�</b>
	 * 
	 * <p>
	 * <b>����</b>��
	 * ��������Ҳ�����Ϣ����Ϸ�ڲ���Ϣʱ�����̱߳����ѣ�û����Ϣʱ�����̱߳����𡣸��߳�
	 * ��Ҫ�������װ����Ϣ�е�<b>����</b>{@link YRequest}��
	 * 
	 * <p>
	 * <b>��ϸ</b>�� ������Ϣ������<b>����</b>����Ӧ��<b>�����߼�</b>
	 * {@link YABaseDomainLogic}����
	 * 
	 * <p>
	 * <b>ע</b>�� ����<b>����</b>���ύ<b>����</b>����һ���߳���
	 * 
	 * @author FeiYiyun
	 */
	private class YMsgThread extends Thread
	{

		/** <b>��Ϣ�̵߳���Ϣѭ��</b>������<b>��Ϸ�߼�</b>�ս�ʱ�˳����̡߳� */
		private Looper lpr;
		private static final String strMsgThreadName = "��Ϣ�߳�";

		private YMsgThread()
		{
			super();
			setName(strMsgThreadName);
		}

		@Override
		public void run()
		{
			// ������Ϣ����
			Looper.prepare();
			// ��ȡ��Ϣѭ�����ã��Ա��˳�
			lpr = Looper.myLooper();
			// ���뱾�߳�ͨ�ŵ���Ϣ�������YGameActivity���Խ�����Ҳ�������������
			// ע���½�Handlerһ��Ҫ��Looper.prepare()��
			// ��Ϸ�е������߼������������Ϣ����Ҳ�����������Ϣ��ͨ������Ϣ������̷߳���Ϣ��
			handlerGameLogic = new YMsgHandler(YAGameLogic.this);
			gm_actvty.setGameLogicHandler(handlerGameLogic);
			Collection<YABaseDomainLogic<?>> values = domainLogics.values();
			// addDomainLogic(YABaseDomainLogic<?>...)�п���Ϊ�գ����¸�ֵһ�Σ�???bug��ʱ�޸�
			for (YABaseDomainLogic<?> domainLogic : values)
				domainLogic.handlerGameLogic = handlerGameLogic;
			// ������Ϣѭ��
			Looper.loop();
		}
	}

	// ???��ȡǿ�����Ƿ���ʣ����ʣ���handler��MsgThread����
	private static class YMsgHandler extends Handler
	{
		private YAGameLogic gameLogic;

		private YMsgHandler(YAGameLogic gameLogic)
		{
			this.gameLogic = gameLogic;
		}

		@Override
		public void handleMessage(Message msg)
		{
			gameLogic.onReceiveRequest((YRequest) msg.obj);
		}
	}

}
