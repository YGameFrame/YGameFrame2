package fyy.ygame_perspective;

import android.util.Log;
import fyy.ygame_frame.base.YABaseDomainData;

/**
*<b>Զ��ͼ����</b>
*
*@author FeiYiyun
*
*/
public class YPerspectivesData extends YABaseDomainData
{
	private static final String strTag = "YPerspectivesData";
	/**<b>Զ��ͼ������</b>*/
	final YPerspective[] perspectives;
	/**<b>��Ϸ��ͼ���</b>*/
	int iViewWidth;

	/**
	*@param iID <b><i>Զ��ͼʵ��</i></b>��ʶ
	*@param perspectives <b>Զ��ͼ��</b>{@link YPerspective}���ɶ��������Ҫ��һ������
	*�����˳��Ϊ��ͼ˳��Ӧ��ע������˳������ͼ�񱻸���
	*/
	public YPerspectivesData(int iID, YPerspective... perspectives)
	{
		super(iID);
		if (null == perspectives)
		{
			Log.e(strTag, "����Ҫ��һ��Զ��ͼ�����");
			throw new NullPointerException("Զ��ͼ��Ϊ��");
		}
		this.perspectives = perspectives;
	}

	@Override
	public void onReceiveBroadcastMsg(int iMsgKey, Object objectDetailMsg)
	{
		// TODO Auto-generated method stub

	}

}
