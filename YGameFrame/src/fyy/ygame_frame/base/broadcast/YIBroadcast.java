package fyy.ygame_frame.base.broadcast;


/**
 * <b>�㲥</b>
 * <p>
 * ��<b>�㲥���սӿ�</b>�㲥��Ϣ
 * </p>
 * <hr>
 * ע��
 * 
 * <li><b>�㲥���սӿ�</b>ͨ��{@link #send(int, Object, YIReceiver)}��<b>�㲥</b>������Ϣ
 * <li>����ĳ��<b>�㲥���սӿ�</b>��<b>�㲥</b>�����Լ���״̬����Ϊ�仯��������ӽ���<b>�㲥</b>
 * ��<b>�㲥���սӿ�</b>���յ�����Ϣ��������������Ϣ�����<b>�㲥���սӿ�</b>�Լ������յ��Լ��㲥����Ϣ��
 * 
 * @author fei yiyun
 * 
 */
/**
*<b>�㲥�ӿ�</b>
*
*<p>
*<b>����</b>��
*·��<b>�㲥���սӿ�</b>{@link YIReceiver}��״̬�仯����ͨ����֮��Ľ�����
*
*<p>
*<b>ע</b>��
*���������Ϣ������<b>�㲥���սӿ�</b>{@link YIReceiver}��
*
*@author FeiYiyun
*
*/
public interface YIBroadcast
{
//	/**<b>��Ϣ</b>��ͬһ<b><i>ʵ��</i></b>�����в�������
//	*<b>����</b>{@link YABaseDomainData}��<b>�߼�</b>{@link YABaseDomainLogic}��
//	*<b>��ͼ</b>{@link YABaseDomainView}������ɡ�*/
//	// ��������ڴ����˸��࣬�����໹û�б������������
//	// Ŀǰ����Ϣ�ķ��������й㲥���սӿ�����㲥��ɺ��͡�
//	public final static int iMSG_AllComponentForThisCreated = 743896;
//	/**<b>��Ϣ</b>��<b>������ͼ</b>{@link YABaseDomainView}������ɡ�*/
//	public final static int iMSG_DomainViewLayouted = 745729;
//	/**<b>��Ϣ</b>��<b>��ͼ��ͼ</b>������ɡ�*/
//	public final static int iMSG_MapViewLayouted = 467023;
//	/**<b>��Ϣ</b>��<b>��ͼ�߼�</b>�߼�ֵȷ����ɡ�*/
//	public final static int iMSG_MapLogicConfirmed = 7496490;

	/**��<b>�㲥�ӿ�</b>{@link YIBroadcast}������Ϣ����ϢӦ����<b>�㲥���սӿ�</b>{@link YIReceiver}
	*��״̬�仯��
	*@param iMsgKey ��Ϣ��ֵ
	*@param objDetailMsg ��Ϣ����ϸ��Ϣ
	*@param receiverSend ���͸���Ϣ��<b>�㲥���սӿ�</b>
	*/
	void send(int iMsgKey, Object objDetailMsg, YIReceiver receiverSend);
}
