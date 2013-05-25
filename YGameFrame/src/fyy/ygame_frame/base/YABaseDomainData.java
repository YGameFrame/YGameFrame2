package fyy.ygame_frame.base;

import fyy.ygame_frame.base.broadcast.YIBroadcast;
import fyy.ygame_frame.base.broadcast.YIReceiver;
import android.content.res.Resources;

/**
 * <b>��������</b>
 * 
 * <p>
 * <b>����</b>�����<b><i>��Ϸʵ��</i></b>����������֮һ�������<b>����</b>
 * ��Ҫ�̳и������ʵ�֡� ������ʵ����<b>�㲥���սӿ� </b> {@link YIReceiver}��
 * ��Ҫ������ӽ���Ӧ��<b><i>��Ϸʵ��</i></b>��<b>�㲥</b>
 * {@link YIBroadcast}�С�
 * 
 * <p>
 * <b>����</b>��һ��<b><i>��Ϸʵ��</i></b>��һ������������Ϊ��������
 * 
 * <p>
 * <b>��ϸ</b>����������Ҫ����������ְ��<br>
 * <li>�־û����ݣ��ӳ־û��豸�б��桢��ȡ���ݣ�
 * <li>��װ���ݣ���װ����ġ������塱�ṩ��<b>�����߼�</b>
 * {@link YABaseDomainLogic} ��<b>������ͼ</b>
 * {@link YABaseDomainView} ʹ�ã�
 * <li>�������ݣ�<b>��ͼ</b>��<b>�߼�</b>���Խ�Ҫ���ݸ��Է������ݹ����ڸö����ϴ��ݹ�ȥ��
 * <p>
 * <b>ע</b>�� ������ʵ����<b>�㲥���սӿ�</b>{@link YIReceiver}
 * ��������������ͬһ<b><i>��Ϸʵ��</i></b> ����������״̬�仯��
 * <p>
 * 
 * @author FeiYiyun
 * 
 */
public abstract class YABaseDomainData implements YIReceiver
{

	/**
	 * <b>����㲥</b>��������ʵ����<b>�㲥���սӿ�</b>{@link YIReceiver}
	 * �� ������������<b>�㲥</b>{@link YIBroadcast}
	 * ��ͨ���ù㲥�����������������ͬһ������ʵ�� ����������������<b>�����߼�</b>
	 * {@link YABaseDomainLogic}�� <b>������ͼ</b>
	 * {@link YABaseDomainView} ʵʱͨ�ţ������Լ���״̬����Ϊ�仯��
	 */
	protected YIBroadcast broadcastDomain;

	/**
	 * <b><i>��Ϸʵ��</i>���</b>��<li>
	 * Ϊ�����ʶ���������ʶ��ͬһ�����ж��ʵ������Щʵ���ı�� ��Ӧ��ͬ�� <li>
	 * ͬһ<b><i>��Ϸʵ��</i></b>��ͬ�����Ķ�������ͬ�ı�ţ�
	 */
	public final int iID;

	/**
	 * @param iID
	 *                <b><i>��Ϸʵ��</i></b>��<b>���</b>
	 *                {@link #iID}
	 */
	protected YABaseDomainData(int iID)
	{
		this.iID = iID;
	}

	/**
	 * @param iID
	 *                <b><i>��Ϸʵ��</i></b>��<b>���</b>
	 *                {@link #iID}
	 * @param resources
	 *                ��Դ
	 * @param strAssetsFileName
	 *                assets�ļ����µ��ļ���
	 */
	protected YABaseDomainData(int iID, Resources resources, String strAssetsFileName)
	{
		this(iID);
	}

	public final void setBroadcast(YIBroadcast broadcast)
	{
		this.broadcastDomain = broadcast;
	}

}
