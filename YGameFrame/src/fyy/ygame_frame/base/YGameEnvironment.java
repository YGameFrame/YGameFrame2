/** */
package fyy.ygame_frame.base;

import fyy.ygame_frame.base.broadcast.YIBroadcast;

/**
*<b>��Ϸ����</b>
*
*<p>
*<b>����</b>��
*��Ϸ����е�ȫ�ֶ��󡢱�������ͨ��������õ������⣬
*�ö��󻹶�����һЩȫ�ֵĳ�������<b>�㲥�ӿ�</b>{@link YIBroadcast}
*��<b>��Ϣ��ֵ</b>{@link BroadcastMsgKey}��
*
*<p>
*<b>����</b>��
*TODO
*
*<p>
*<b>��ϸ</b>��
*TODO
*
*<p>
*<b>ע</b>��
*TODO
*
*<p>
*<b>��</b>��
*TODO
*
*@author FeiYiyun
*
*/
public final class YGameEnvironment
{
	/**
	*<b>�㲥��Ϣ��ֵ</b>
	*
	*<p>
	*<b>����</b>��
	*������һЩ<b>�㲥�ӿ�</b>{@link YIBroadcast}���͵���Ϣ��ֵ
	*
	*@author FeiYiyun
	*
	*/
	public final static class BroadcastMsgKey
	{
		/**<b>��Ϣ</b>����ͼ��ͼ������ɡ�*/
		public static final int MSG_MAP_VIEW_LAYOUTED = 10001;
		/**<b>��Ϣ</b>����ͼ�߼�ֵ������Ļ�����ĵ�ͼ��ȵȣ�ȷ����*/
		public static final int MSG_MAP_LOGIC_CONFIRMED = 10002;
		/**<b>��Ϣ</b>��<b>������ͼ</b>{@link YABaseDomainView}������ɡ�*/
		public static final int MSG_DOMAIN_VIEW_LAYOUTED = 10003;
		/**<b>��Ϣ</b>��<b><i>��Ϸʵ��</i></b>���в���������ɡ�*/
		public static final int MSG_DOMAIN_FINISHED = 10004;
	}

	private YGameEnvironment()
	{
	}
}
