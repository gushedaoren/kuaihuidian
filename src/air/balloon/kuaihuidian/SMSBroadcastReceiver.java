package air.balloon.kuaihuidian;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSBroadcastReceiver extends BroadcastReceiver {
	
	SharedPreferences sp;
	@Override
	public void onReceive(Context context, Intent intent) {

		sp=context.getSharedPreferences("sp", Context.MODE_PRIVATE);
		Object[] pduses = (Object[]) intent.getExtras().get("pdus");
		for (Object pdus : pduses) {
			byte[] pdusmessage = (byte[]) pdus;
			SmsMessage sms = SmsMessage.createFromPdu(pdusmessage);
			String mobile = sms.getOriginatingAddress();// 发送短信的手机号码
			String content = sms.getMessageBody(); // 短信内容
			Date date = new Date(sms.getTimestampMillis());
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String time = format.format(date); // 得到发送时间
			
			
			Log.i(getClass().getSimpleName(),"mobile:"+mobile);
			Log.i(getClass().getSimpleName(),"content:"+content);
			
		    String savedPhone=sp.getString("phone", "10086");
		    
		    if(mobile.contains(savedPhone)&&content.contains("#khd")){
		    	kuaihuidian(context,savedPhone);
		    }

		}
	}
	private void kuaihuidian(Context context,String phone) {
		// TODO Auto-generated method stub
		
		Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phone));
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
		
	}
}