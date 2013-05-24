package fyy.ygame_frame.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.util.EncodingUtils;

import android.content.res.Resources;
import android.util.Log;

/**
 * <b>�ļ�����</b><br>
 * 
 * @author fei yiyun
 * 
 */
/**
*<b>�ļ�����</b>
*
*<p>
*<b>����</b>��
*�����ļ������ķ��㹤��
*
*@author FeiYiyun
*
*/
public final class YFileUtil
{
	private static final String strTag = "YFileUtil";

	private YFileUtil()
	{
	}

	/**��assets�ļ����е��ļ���ȡ�ı���
	*@param res ��Դ
	*@param strFileName �ļ���
	*@return ��ȡ�����ַ���
	*/
	public static String readFileFromAssets(Resources res, String strFileName)
	{
		InputStream inpt_strm = null;
		int iLenght = 0;
		byte[] byteBuffer = null;

		try
		{
			// ��ȡ�ļ�
			inpt_strm = res.getAssets().open(strFileName);
			// ��ȡ�ļ�����
			iLenght = inpt_strm.available();
			// �����ļ����ȿ�����Ӧ���ڴ�
			byteBuffer = new byte[iLenght];
			// ���ļ����ݶ����ڴ�
			inpt_strm.read(byteBuffer);
			// �ر��ļ�
			inpt_strm.close();
			// ���ڴ��е�ֵ����ΪUTF-8�ַ���
			return EncodingUtils.getString(byteBuffer, "UTF-8");
		} catch (IOException e)
		{
			Log.e(strTag, "��ȡ�ļ���" + strFileName + "ʧ��");
			e.printStackTrace();
			throw new RuntimeException("��ȡ�ļ�ʧ��");
		} finally
		{
			if (null != inpt_strm)
				try
				{
					inpt_strm.close();
				} catch (IOException e)
				{
					Log.e(strTag, "�ر��ļ�" + strFileName + "ʧ��");
					e.printStackTrace();
				}
		}
	}
}
