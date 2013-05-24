package fyy.ygame_frame.base;

import java.util.Map;

import android.os.Handler;
import android.util.Log;
import fyy.ygame_frame.base.YDrawInformation.YDrawInfoForm;
import fyy.ygame_frame.base.broadcast.YIBroadcast;
import fyy.ygame_frame.base.broadcast.YIReceiver;
import fyy.ygame_frame.extra.YITask;
import fyy.ygame_frame.extra.YRequest;

/**
*<b>�����߼�</b>
*
*<p>
*<b>����</b>�����<b><i>��Ϸʵ��</i></b>����������֮һ�������<b>�߼�</b>��Ҫ�̳и������ʵ�֡�
*��<b>��Ϸ�߼�</b>{@link YAGameLogic}����Ϊ���ܱ����������Ҫͨ��
*{@link YAGameLogic#addDomainLogic(YABaseDomainLogic...)}
*��ӱ����󡣴��⣬������ʵ����<b>�㲥���սӿ�</b>{@link YIReceiver}����Ҫ������ӽ�
*��Ӧ��<b><i>��Ϸʵ��</i></b>��<b>�㲥</b>{@link YIBroadcast}�С� <br>
*ͬʱ���ö���Ҳ��<b><i>��Ϸʵ��</i></b>֮�佻������ڡ�
*
*<p>
*<b>����</b>��һ��<b><i>��Ϸʵ��</i></b>��һ������������Ϊ��������
*
*<p>
*<b>��ϸ</b>����������Ҫ����������ְ��<br>
*<li>�������󣺲μ�{@link #onDealRequest(YRequest)}��
*<li>�ύ���񣺲μ�{@link #onSubmitCurrentTasks()}��
*<li>�������󣺲μ�{@link #onSendRequests(Map)};
*
*<p>
*<b>ע</b>��
*<li>{@link #onDealRequest(YRequest)}��
*{@link #onSubmitCurrentTasks()}��{@link #onSendRequests(Map)}
*�Ǳ�����첽���õġ�
*<li>
*�ö�������ʵ����<b>�㲥���սӿ�</b>{@link YIReceiver}��
*����������������<b>�߼�</b>�Լ�ͬһ<b><i>��Ϸʵ��</i></b>����������״̬�仯��
*
*@author FeiYiyun
*
*@param <D> <b>��������</b>{@link YABaseDomainData}��������
*/
public abstract class YABaseDomainLogic<D extends YABaseDomainData> implements YIReceiver
{
	private static final String strTag = "YABaseDomainLogic";

	/**<b>����</b>������߼�������<b>��������</b>{@link YABaseDomainData}��*/
	protected final D domainData;

	/**
	 * <b><i>��Ϸʵ��</i>���</b>��<li>Ϊ�����ʶ���������ʶ��ͬһ�����ж��ʵ����
	 * ��Щʵ���ı�Ų�Ӧ��ͬ�� <li>
	 * ͬһ<b><i>��Ϸʵ��</i></b>��ͬ�����ı����һ�µġ�
	 */
	public final int iID;

	/**<b>����㲥</b>��������������<b><i>��Ϸʵ��</i></b><b>�㲥</b>{@link YIBroadcast}
	 *�����ڷ����Լ���״̬�仯������������*/
	protected YIBroadcast broadcastDomain;

	/**<b>�߼��㲥</b>��������������<b><i>�߼�</i></b><b>�㲥</b>{@link YIBroadcast}
	 * ������������<b>�����߼�</b> {@link YABaseDomainLogic}�����Լ���״̬�仯*/
	protected YIBroadcast broadcastLogic;

	/**<b>��Ϣ���</b>����<b>��Ϸ�߼�</b>{@link YAGameLogic}������Ϣ�ľ��*/
	protected Handler handlerGameLogic;

	// ��ֹ��ͼ���߼������ݲ������ţ����Խ����Ļ�ͼ��Ϣ��һ��������
	final YDrawInfoForm drawInfoForm;

	/**
	*@param domainData  ��֮������<b>��������</b>{@link #domainData}������Ϊ�ա�
	*/
	public YABaseDomainLogic(D domainData)
	{
		if (null == domainData)
		{
			Log.e(strTag, "�뱾������ص����ݡ���domainData����Ϊ��");
			throw new NullPointerException("�뱾������ص����ݡ���domainDataΪ��");
		}
		this.domainData = domainData;
		this.iID = this.domainData.iID;
		this.drawInfoForm = new YDrawInfoForm();
	}

	protected void initializeDrawInfoForm(YDrawInfoForm drawInfoForm)
	{

	}
	
	@Override
	public void onReceiveBroadcastMsg(int iMsgKey, Object objectDetailMsg)
	{
		switch (iMsgKey)
		{
		case YGameEnvironment.BroadcastMsgKey.MSG_DOMAIN_FINISHED:
			initializeDrawInfoForm(drawInfoForm);
			break;

		default:
			break;
		}
	}

	/**���ܻ�����<b>�����߼�</b>{@link YABaseDomainLogic}����<b>����</b>{@link YRequest} ��
	 *<b>����</b>Ӧ��װ��msg.what�У�����Ϣ�ķ�ʽͨ��{@link #handlerGameLogic}���ͳ�ȥ
	*@param domainLogics �߼��б�
	*/
	protected abstract void onSendRequests(Map<Integer, YABaseDomainLogic<?>> domainLogics);

	/**�����ύ������Ҫ�����<b>����</b>�б�{@link YITask}
	*@return <b>����</b>�б�
	*/
	protected abstract YITask[] onSubmitCurrentTasks();

	/**��������<b>�����߼�</b>{@link YABaseDomainLogic}������<b>����</b>
	 *{@link YRequest} ����ҷ�����<b>����</b>��������ɺ�������Ӧ�ļ�¼��
	 *�Ա����ܽ���<b>����</b> {@link YITask}��ʱ����Ϊ����
	*@param request �յ���<b>����</b>
	*@return <b>����</b>�����Ľ����
	*/
	protected abstract int onDealRequest(YRequest request);

	public final void setBroadcast(YIBroadcast broadcast)
	{
		this.broadcastDomain = broadcast;
	}

}
