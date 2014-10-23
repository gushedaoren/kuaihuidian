package air.balloon.kuaihuidian;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	Button btn;
	EditText editText;
	
	SharedPreferences sp;
	MainService mainService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        sp=this.getSharedPreferences("sp", MODE_PRIVATE);
		btn=(Button) findViewById(R.id.btn);
		
		
		editText=(EditText) findViewById(R.id.edit);
		
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				sp.edit().putString("phone", editText.getText().toString()).commit();
				Toast.makeText(MainActivity.this, "设置成功", 3000).show();
				
				Intent intent=new Intent(MainActivity.this, MainService.class);  
				
				startService(intent);
			    
				
			}
		});
		
		
	}

	
}
