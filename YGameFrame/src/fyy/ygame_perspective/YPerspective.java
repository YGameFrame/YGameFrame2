package fyy.ygame_perspective;

import fyy.ygame_frame.base.YGameView;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

/**
 * <b>Զ��ͼ��</b>
 * 
 * <p>
 * <b>����</b>�� һ������Ϸ������ѭ�����ŵġ�Զ��ͼ������ɽ����ա��Ʋʵȡ�
 * 
 * <p>
 * <b>ע</b>�� ��Ϊ<b>Զ��ͼ</b>��ͼƬ�زģ�Ӧ��ѭ������ʱ��û�з�϶������ͼƬ������ܹ���ͼƬ�� �Ҳ��޷��ϣ�
 * 
 * @author FeiYiyun
 * 
 */
public final class YPerspective
{
	private static final String strTag = "YPerspective";

	/** <b>��Դ��ʶ</b> */
	private final int iR_drawable;
	/** <b>��Ӧλͼ����</b> */
	private Bitmap bitmap;

	/** <b>��������</b>�������<b>��Ϸ��ͼ</b>{@link YGameView}��ȡ� */
	float fStepLength = 1 / 20;
	/** <b>ʵ�ʲ���</b> */
	int iStepLength;

	/** <b>Զ��ͼ����</b>����<b>��Ϸ��ͼ</b>{@link YGameView}��ȡ� */
	private int iWidth;
	/** <b>Զ��ͼ��ʵ�ʸ߶�</b> */
	private int iHeight;
	/**
	 * <b>Զ��ͼ��Ȩ��</b>����<b>Զ��ͼ��</b>{@link YPerspective}�����߶ȣ������ <b>��Ϸ��ͼ</b>
	 * {@link YGameView}����
	 */
	private final float fWeight;

	/** <b>��ʼʵ�ʺ�����</b> */
	int iInitX;
	/** <b>��ʼʵ��������</b> */
	private int iInitY;
	/** <b>��ʼ����������</b> */
	private final float fInitX;
	/** <b>��ʼ����������</b> */
	private final float fInitY;

	/**
	 * �����ġ�������������ΧӦΪ0~1���ñ��������<b>��Ϸ��ͼ</b>{@link YGameView}��
	 * 
	 * @param iR_drawable
	 *                Զ��ͼ����Դ��ʶ��R�ļ���drawableֵ��
	 * @param fStepLength
	 *                Զ��ͼ�ƶ��ı���������Զ��ͼ�ƶ�һ�εġ��������ȡ������
	 *                ��<b>��Ϸ��ͼ</b>��ȣ���Ĭ��Ϊ1/20����Χ����0��С��1
	 * @param fWeight
	 *                Զ��ͼȨ�أ���ָ�ݷ����ϣ�����ΧӦ�ô���0��С�ڵ���1
	 * @param fInitX
	 *                Զ��ͼ��ʼ�����������ꡱ�������<b>��Ϸ��ͼ</b>��ȣ���Χ Ӧ�ô��ڵ���0 �� С�ڵ���1��
	 * @param fInitY
	 *                Զ��ͼ��ʼ�����������ꡱ�������<b>��Ϸ��ͼ</b>�߶ȣ���Χ Ӧ�ô��ڵ���0 �� С�ڵ���1��
	 */
	public YPerspective(int iR_drawable, float fStepLength, float fWeight,
			float fInitX, float fInitY)
	{
		if (fStepLength >= 1 || fStepLength <= 0 || fWeight > 1
				|| fWeight <= 0 || fInitX > 1 || fInitX < 0
				|| fInitY > 1 || fInitY < 0)
		{
			Log.e(strTag, "����������ΧӦ�ô���0��С��1��Ȩ�ط�ΧӦ�ô���0��С�ڵ���1����ʼ����"
					+ "�ᡢ�����귶ΧӦ�ô��ڵ���0 �� С�ڵ���1");
			throw new IllegalArgumentException(
					"����������Ȩ�ػ��ʼ�����������곬����Χ��");
		}
		this.iR_drawable = iR_drawable;
		this.fWeight = fWeight;
		this.fStepLength = fStepLength;
		this.fInitX = fInitX;
		this.fInitY = fInitY;
	}

	/**
	 * ���øú����Ķ���Ӧ�ø���������Ϸ��ԡ�
	 * 
	 * @param fStepLength
	 */
	void setStepLenght(float fStepLength)
	{
		this.fStepLength = fStepLength;
		iStepLength = (int) (fStepLength * iWidth);
	}

	/**
	 * ����λͼ
	 * 
	 * @param res
	 *                ��Դ
	 * @param iViewWidth
	 *                ��ͼ���
	 * @param iViewHeight
	 *                ��ͼ�߶�
	 */
	void generateBitmap(Resources res, int iViewWidth, int iViewHeight)
	{
		iInitX = (int) (iViewWidth * fInitX);
		iInitY = (int) (iViewHeight * fInitY);

		iWidth = iViewWidth;
		iHeight = (int) (iViewHeight * fWeight);

		iBottom = iHeight + iInitY;

		iStepLength = (int) (fStepLength * iViewWidth);

		Bitmap bitmapTem = BitmapFactory.decodeResource(res,
				iR_drawable);
		bitmap = Bitmap.createScaledBitmap(bitmapTem, iWidth, iHeight,
				true);
		if (!bitmapTem.isRecycled())
			bitmapTem.recycle();
	}

	private int iBottom;

	/**
	 * ����Զ��ͼ��Ӧ���ڵ�����{@link #generateBitmap(Resources, int, int)}֮����ø÷�����
	 * 
	 * @param canvas
	 *                ����
	 * @param iX
	 *                Զ��ͼ������Ļ�����ĺ����꣨�ú�������λ��Զ��ͼ�ϵģ�
	 */
	void draw(Canvas canvas, int iX)
	{
		int iiX = iWidth - iX;
		canvas.drawBitmap(bitmap, new Rect(iX, 0, iWidth, iHeight),
				new Rect(0, iInitY, iiX, iBottom), null);
		canvas.drawBitmap(bitmap, new Rect(0, 0, iX, iHeight),
				new Rect(iiX, iInitY, iWidth, iBottom), null);
	}

	/**
	 * ����λͼ
	 */
	void recycle()
	{
		if (null != bitmap && !bitmap.isRecycled())
			bitmap.recycle();
	}
}
