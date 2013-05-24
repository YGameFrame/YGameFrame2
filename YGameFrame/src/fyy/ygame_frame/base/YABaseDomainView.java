package fyy.ygame_frame.base;

import fyy.ygame_frame.base.broadcast.YIBroadcast;
import fyy.ygame_frame.base.broadcast.YIReceiver;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;

/**
*<b>������ͼ</b>
*
*<p>
*<b>����</b>�����<b><i>��Ϸʵ��</i></b>����������֮һ�������<b>��ͼ</b>��Ҫ�̳и����
*��ʵ�֡���<b>��Ϸ��ͼ</b>{@link YGameView} ����
*Ϊ���ܱ����������Ҫͨ�� {@link YGameView#addDomainView(YABaseDomainView...)}��ӱ�����
*���⣬������ʵ����<b>�㲥���սӿ�</b>{@link YIReceiver}����Ҫ������ӽ���Ӧ��<b><i>��Ϸʵ��
*</i></b>��<b>�㲥</b>{@link YIBroadcast}�С�
*
*<p>
*<b>����</b>��һ��<b><i>��Ϸʵ��</i></b>��һ������������Ϊ��������
*
*<p>
*<b>��ϸ</b>����������Ҫ����������ְ��<br>
*<li>������Ļ���μ�{@link #onLoadBitmaps(Resources, int, int, int)}��
*<li>��ͼ���μ�{@link #onDraw(Canvas, YDrawInformation)}��
*<li>����λͼ���μ�{@link #onRecycleBitmaps()};
*
*<p>
*<b>ע</b>���ö�������ʵ����<b>�㲥���սӿ�</b>{@link YIReceiver}������������������<b>��ͼ</b>
*�Լ�ͬһ<b><i>��Ϸʵ��</i></b>����������״̬�仯��
*
*@author FeiYiyun
*
*@param <D> <b>��������</b>{@link YABaseDomainData}��������
*/
public abstract class YABaseDomainView<D extends YABaseDomainData> implements YIReceiver
{
	private static final String strTag = "YABaseDomainView";

	/**<b>����㲥</b>��������������<b><i>��Ϸʵ��</i></b><b>�㲥</b>{@link YIBroadcast}��
	 * ���ڷ����Լ���״̬�仯������������*/
	protected YIBroadcast broadcastDomain;

	/**<b>��ͼ�㲥</b>��������������<b><i>��ͼ</i></b><b>�㲥</b>{@link YIBroadcast}��
	 * ����������<b>������ͼ</b>{@link YABaseDomainView}�����Լ���״̬�仯��*/
	protected YIBroadcast broadcastView;

	/**<b>����</b>�������ͼ������<b>��������</b>{@link YABaseDomainData}*/
	final protected D domainData;

	// ��ͼ��ţ� ���߼�һ��
	final int iID;

	/**
	 * @param domainData
	 *                ������<b>��������</b>{@link YABaseDomainData}
	 */
	public YABaseDomainView(D domainData)
	{
		if (null == domainData)
		{
			Log.e(strTag, "�뱾������ص����ݡ���domainData����Ϊ��");
			throw new NullPointerException("�뱾������ص����ݡ���domainDataΪ��");
		}
		this.domainData = domainData;
		this.iID = this.domainData.iID;
	}

	/**����λͼ��Դ����Ļ������ڴ���ɡ�Ӧ������<b>iMapLayoutParams</b>��
	*@param resources ��Դ
	*@param iWidth <b>��Ϸ��ͼ</b>{@link YGameView}�Ŀ��
	*@param iHeight <b>��Ϸ��ͼ</b>�ĸ߶�
	*@param iMapLayoutParams �ɡ���ͼ��������ɺ󣬽�һ��ȷ���Ĳ��ֲ�������������
	*<b>������ͼ</b>{@link YABaseDomainView}����Ļ����ʱ�Ĳο������ľ���
	*��ֵ����Ϸ�в��ú��֡���ͼ���йء�<br>
	*��ν����ͼ����Ϊ<b>������ͼ</b>�����࣬��ͨ��Ӧ���ǵ�һ����ӵ�<b>��Ϸ��ͼ</b>
	*{@link YGameView}�е�<b>������ͼ</b>�������������ɺ����б�Ҫ�Ļ�����Ӧ�ø������
	*�ò���������ò���Ϊ�ա�<br>
	*������һ����Ϊ����ͼ����<b>������ͼ</b>������ò���ʾ�����룺<br>
	*/
	protected abstract void onLoadBitmaps(Resources resources, int iWidth, int iHeight,
			int[] iMapLayoutParams);

	/**��ͼ���ڻ����ϸ���<b>��ͼ��Ϣ</b>{@link YDrawInformation}���ƻ���
	*@param canvas ����
	*@param drawInformation <b>��ͼ��Ϣ</b>{@link YDrawInformation}
	*/
	protected abstract void onDraw(Canvas canvas, YDrawInformation drawInformation);

	/**���ո���λͼ����
	*/
	protected abstract void onRecycleBitmaps();

	public final void setBroadcast(YIBroadcast broadcast)
	{
		this.broadcastDomain = broadcast;
	}
}
