package fyy.ygame_frame.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


/**
*<b>ͼ�񹤾�</b>
*
*<p>
*<b>����</b>��
*����ͼ�񷽱�Ĺ��ߡ�
*
*@author FeiYiyun
*
*/
public final class YImageUtil
{
	private YImageUtil()
	{
	};

	/**���ݴ����id���鷵�ض�Ӧ��λͼ���顣
	*@param res ��Դ
	*@param iImgIds id����
	*@return λͼ����
	*/
	public static Bitmap[] getBitmapArray(Resources res, int[] iImgIds)
	{
		Bitmap[] btmp_arr = new Bitmap[iImgIds.length];
		for (int i = 0; i < iImgIds.length; i++)
			btmp_arr[i] = BitmapFactory.decodeResource(res,
					iImgIds[i]);

		return btmp_arr;
	}

	/**��������������ε�λͼ���顣<br>
	 * <b>ע</b>��ԭλͼ����ᱻ���ա�
	*@param bitmaps Ҫ��������λͼ����
	*@param iSideLength ��Ҫ������ɵı߳�
	*@return ��������λͼ����
	*/
	public static Bitmap[] stretchImageArray(Bitmap bitmaps[],
			int iSideLength)
	{
		Bitmap[] btmp_arrStretched = new Bitmap[bitmaps.length];

		for (int i = 0; i < bitmaps.length; i++)
		{
			btmp_arrStretched[i] = Bitmap.createScaledBitmap(
					bitmaps[i], iSideLength, iSideLength,
					true);
			if (null != bitmaps[i] && !bitmaps[i].isRecycled())
				bitmaps[i].recycle();
		}
		return btmp_arrStretched;
	}

	/**����ָ����������ָ���λͼ����<br>
	 * <b>ע</b>��ԭλͼ����ᱻ���ա�
	*@param iRowSum ָ������
	*@param iColumnSum  ָ������
	*@param btmpSrc  ָ��λͼ����
	*@return ��ֺ��λͼ��������
	*/
	public static Bitmap[] splitImage(int iRowSum, int iColumnSum,
			Bitmap btmpSrc)
	{
		int iSideLength = btmpSrc.getHeight() / iRowSum;

		Bitmap[] btmp_arr = new Bitmap[iColumnSum * iRowSum];
		for (int i = 0; i < iRowSum; i++)
			for (int j = 0; j < iColumnSum; j++)
				btmp_arr[i * iColumnSum + j] = Bitmap
						.createBitmap(btmpSrc,
								j * iSideLength,
								i * iSideLength,
								iSideLength,
								iSideLength);

		if (!btmpSrc.isRecycled())
			btmpSrc.recycle();

		return btmp_arr;
	}

	/**����λͼ���������λͼ��
	*@param bitmaps ��Ҫ������λͼ��λͼ���󣬿ɶ��
	*/
	public static void recycleBitmapArray(Bitmap[]... bitmaps)
	{
		for (int i = 0; i < bitmaps.length; i++)
			for (int j = 0; j < bitmaps[i].length; j++)
				if (null != bitmaps[i][j]
						&& !bitmaps[i][j]
								.isRecycled())
					bitmaps[i][j].recycle();
	}
}
