package fyy.ygame_frame.base;

import fyy.ygame_frame.extra.YRequest;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

/**
*<b>��Ϸ�</b>
*
*<p>
*<b>����</b>��
*��Ҫְ��Ϊ������Ҳ���������<b>����</b>{@link YRequest}��װ��msg.obj�У�ͨ��
*<b>��Ϸ�߼���Ϣ���</b>{@link #handlerGameLogic}����Ϣ���͵�<b>��Ϸ�߼�</b>{@link YAGameLogic}��
*
*<p>
*<b>ע</b>��
*�ûĬ������Ϊȫ�����ޱ�������������
*
*@author FeiYiyun
*
*/
public class YGameActivity extends Activity
{
	/**<b>��Ϸ�߼���Ϣ���</b>��<b>��Ϸ�߼�</b>{@link YAGameLogic}����Ϣ�����Ӧ�����
	*����������<b>����</b>{@link YRequest}��װ��msg.obj�У�ͨ���þ����<b>��Ϸ�߼�</b>���͡�*/
	protected Handler handlerGameLogic;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// ���ú���
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		// ����ȫ��
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	/**����<b>��Ϸ�߼�</b>{@link YAGameLogic}����Ϣ���
	*@param handlerGameLogic ��Ϸ�߼���Ϣ���
	*/
	void setGameLogicHandler(Handler handlerGameLogic)
	{
		this.handlerGameLogic = handlerGameLogic;
	}
}
