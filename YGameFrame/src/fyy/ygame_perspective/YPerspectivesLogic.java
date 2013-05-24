package fyy.ygame_perspective;

import java.util.Map;

import android.util.Log;

import fyy.ygame_frame.base.YABaseDomainLogic;
import fyy.ygame_frame.base.YDrawInformation.YDrawInfoForm;
import fyy.ygame_frame.base.YGameView;
import fyy.ygame_frame.extra.YITask;
import fyy.ygame_frame.extra.YRequest;

/**
*<b>Զ��ͼ�߼�</b>
*
*<p>
*<b>����</b>��
*���ƶ��<b>Զ��ͼ��</b>{@link YPerspective}����Ϊ�����ƶ�����ͣ�ȡ�
*��ν<b>Զ��ͼ</b>ָ������Ϸ������Զ���ı�����
*����ա��Ʋʡ�ɽ�ȡ�ʹ��<b>Զ��ͼ</b>������Ⱦ���õĻ���Ч����
*
*<p>
*<b>ע</b>��
*����<b>Զ��ͼ��</b>�ƶ��ٶ�Ӧ����ѭ���Խ���Զ�ٶ��ɿ쵽������ԭ��
*
*@author FeiYiyun
*
*/
public class YPerspectivesLogic extends YABaseDomainLogic<YPerspectivesData>
{
	private static final String strTag = "YPerspectivesLogic";

	private YPerspective[] perspectives;

	private MoveTask[] moveTasks;
	private YState state;

	/**<b>Զ��ͼ��ʼ���Ƶ������</b>����ν�����Ƶ㡱ָ���Ǵ�<b>Զ��ͼ</b>
	*{@link YPerspective}���ĵ㿪ʼ����*/
	private int[] iXs;

	public YPerspectivesLogic(YPerspectivesData domainData)
	{
		super(domainData);
		perspectives = domainData.perspectives;
		state = YState.NO_SCROLL;
		moveTasks = new MoveTask[]
		{ new MoveTask() };
	}

	@Override
	protected void initializeDrawInfoForm(YDrawInfoForm drawInfoForm)
	{
		super.initializeDrawInfoForm(drawInfoForm);
		iXs = new int[perspectives.length];
		for (int i = 0; i < perspectives.length; i++)
			iXs[i] = perspectives[i].iInitX;
		drawInfoForm.objExtra = iXs;
	}

	/**����<b>Զ��ͼ��</b>���������������飬��<b>Զ��ͼ��</b>�ƶ�һ�����ƶ��ġ��������ȡ�
	*���ñ��������<b>��Ϸ��ͼ</b>{@link YGameView}��ȣ���<br>
	*<b>ע</b>��<li>����Ԫ��Ӧ��<b>Զ��ͼ����</b>{@link YPerspectivesData}�е�
	*<b>Զ��ͼ��</b>{@link YPerspective}һһ��Ӧ��
	*<li>����������ΧӦΪ����0��С��1��
	*@param fStepLengths ������������
	*/
	public void setStepLengths(float[] fStepLengths)
	{
		if (fStepLengths.length != perspectives.length)
		{
			Log.e(strTag, "��������Ӧ��һһ��Ӧ" + "����Ӧ��Զ��ͼ�㣬" + "���˴������������鳤��Ϊ��"
					+ fStepLengths.length + "��Զ��ͼ����ĿΪ��" + perspectives.length);
			throw new IllegalArgumentException("�����������鳤����Զ��ͼ����Ŀ����Ӧ");
		}

		for (int i = 0; i < perspectives.length; i++)
		{
			if (fStepLengths[i] <= 0 || fStepLengths[i] >= 1)
			{
				Log.e(strTag, "��������Ӧ�ô���0��С��1");
				throw new IllegalArgumentException("��������������Χ");
			}

			perspectives[i].setStepLenght(fStepLengths[i]);
		}
	}

	/**��ȡ<b>Զ��ͼ��</b>���������������顣������������ָ<b>Զ��ͼ��</b>�ƶ�һ�����ƶ���
	*���������ȡ����ñ��������<b>��Ϸ��ͼ</b>{@link YGameView}��ȣ���
	*@return ������������
	*/
	public float[] getStepLengths()
	{
		float[] fStepLengths = new float[perspectives.length];
		for (int i = 0; i < perspectives.length; i++)
			fStepLengths[i] = perspectives[i].fStepLength;
		return fStepLengths;
	}

	@Override
	protected void onSendRequests(Map<Integer, YABaseDomainLogic<?>> domainLogics)
	{
		// TODO
	}

	@Override
	protected YITask[] onSubmitCurrentTasks()
	{
		switch (state)
		{
		case LEFT_SCROLL:
		{
			moveTasks[0].bLeft = true;
			return moveTasks;
		}

		case RIGHT_SCROLL:
		{
			moveTasks[0].bLeft = false;
			return moveTasks;
		}

		case NO_SCROLL:
		default:
			return null;
		}
	}

	@Override
	protected int onDealRequest(YRequest request)
	{
		switch (request.iKey)
		{
		case YPerspectivesConstant.RequestKey.LEFT_SCROLL:
			if (request.bStart)
				state = YState.LEFT_SCROLL;
			else
				state = YState.NO_SCROLL;
			break;

		case YPerspectivesConstant.RequestKey.RIGHT_SCROLL:
			if (request.bStart)
				state = YState.RIGHT_SCROLL;
			else
				state = YState.NO_SCROLL;

		default:
			break;
		}
		return 0;
	}

	private static enum YState
	{
		NO_SCROLL, LEFT_SCROLL, RIGHT_SCROLL;
	}

	private class MoveTask implements YITask
	{
		private boolean bLeft = true;
		private int iCountSum = 1;
		private int iCount;

		@Override
		public void execute(YDrawInfoForm drawInfoForm,
				Map<Integer, YABaseDomainLogic<?>> domainLogics)
		{
			if (iCountSum != (iCount = iCount % iCountSum + 1))
				return;

			int[] iTempXs = new int[perspectives.length];
			for (int i = 0; i < perspectives.length; i++)
				if (bLeft)
				{
					iXs[i] += perspectives[i].iStepLength;
					if (iXs[i] >= domainData.iViewWidth)
						iXs[i] -= domainData.iViewWidth ;
					iTempXs[i] = iXs[i];
				} else
				{
					iXs[i] -= perspectives[i].iStepLength;
					if (iXs[i] < 0)
						iXs[i] += domainData.iViewWidth;
					iTempXs[i] = iXs[i];
				}
			if(iTempXs[0] == 25)
			{
				System.out.println("fsdfsd" + ((int[]) (drawInfoForm.objExtra))[0]);
			}
			drawInfoForm.objExtra = iTempXs;
		}
	}

}
