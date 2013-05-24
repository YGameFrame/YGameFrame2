package fyy.ygame_frame.extra;

import fyy.ygame_frame.base.YABaseDomainLogic;
import fyy.ygame_frame.base.YAGameLogic;

/**
 * <b>����</b><br>
 * <p>
 * ��UI����Ҳ�����������Ϸ�ڲ����м���õ���ͨ����Ϸ�߼���� {@link YAGameLogic#handlerGameLogic} �ύ��
 * ��ָ��ĳ���򣨶���������߼����� {@link #dmn_lgc_arrTaskExecutors}��ʼ����� {@link #bStart}
 * ĳ����ͨ���ƶ���������� {@link #iTaskKey} ����<br>
 * �����������ܲ���ִ�У�Ҳ��������ִ�У�Ҳ����ͬ��ִ�У�������ȼ�{@link #iPriority}�йأ�<br>
 * 
 * <b>����أ�����Ҫ��װ��msg.obj�У�</b>
 * 
 * @author fei yiyun
 * 
 */
/**
*<b>����</b>
*
*<p>
*<b>����</b>��
*������<b>��Ҳ���</b>��<b>��Ϸ�ڲ�����</b>��ͨ��<b>��Ϸ�߼�</b>
*{@link YAGameLogic}����Ϣ�������<b>��Ϸ�߼�</b>�������Ӧ��
*<b>�����߼�</b>{@link YABaseDomainLogic}�յ���
*
*<p>
*<b>ע</b>��
*<li>һ��<b>����</b>�����<b>��ʼ</b>��<b>����</b>ĳ������������<b>����</b>ʱӦ��ע����㡣
*<li><b>����</b>����һ����������Ҫ����Ϸ�ľ����龰������
*<li>һ��<b>����</b>����һ����Ӧ��һ��<b>����</b>{@link YITask}��
*
*@author FeiYiyun
*
*/
public final class YRequest
{
	/**<b>��������ȼ�</b>*/
	public final int iPriority;
	/** �������������� */
	/**<b>����ļ�ֵ</b>*/
	public final int iKey;
	/**<b>����Ľ�����</b>��Ϊ<b>�����߼�</b>{@link YABaseDomainLogic}*/
	public final YABaseDomainLogic<?>[] domainLogicReceivers;

	/**<b>����ĸ�����Ϣ</b>*/
	public Object objExtra;
	/**<b>����ĸ�����Ϣ</b>*/
	public int iArg1;
	/**<b>����ĸ�����Ϣ</b>*/
	public int iArg2;
	/**<b>�����ʶ</b>������ĳ������ǿ�ʼ���ǽ�����Ĭ��Ϊ�档*/
	public boolean bStart = true;

	/**
	*@param iPriority <b>����</b>{@link YRequest}�����ȼ�
	*@param iKey <b>����</b>�ļ�ֵ
	*@param domainLogicReceivers <b>����</b>�����ߣ��ɶ��
	*/
	public YRequest(int iPriority, int iKey,
			YABaseDomainLogic<?>... domainLogicReceivers)
	{
		this.iPriority = iPriority;
		this.iKey = iKey;
		this.domainLogicReceivers = domainLogicReceivers;
	}

}
