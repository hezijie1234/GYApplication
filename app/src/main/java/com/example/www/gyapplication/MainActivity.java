package com.example.www.gyapplication;

import org.json.JSONException;
import org.json.JSONObject;

import net.babelstar.common.http.AbstractAsyncResponseListener;
import net.babelstar.common.http.AsyncHttpClient;
import net.babelstar.common.play.RealPlay;
import net.babelstar.common.play.Talkback;
import net.babelstar.common.play.Monitor;
import net.babelstar.common.play.VideoView;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.babelstar.gviewer.NetClient;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import com.google.code.microlog4android.Level;

public class MainActivity extends Activity {
	private boolean mIsStartAV = false;
	private VideoView mVideoImage1;
	private VideoView mVideoImage2;
	private RealPlay mRealPlay1;
	private RealPlay mRealPlay2;
	private Talkback mTalkback;
	private String mServer;
	private String mDevIdno;
	
	private Monitor mMonitor;
	private EditText mEtServer;
	private EditText mEtDevIdno;
	private Button mBtnSound1;
	private Button mBtnSound2; 
	private Button mBtnStart;
	private Button mBtnStop; 
	private Button mBtnRecord; 
	private Button mBtnTalkStart;
	private Button mBtnTalkStop;
	
	private Button mBtnMonitorStart;
	private Button mBtnMonitorStop;
	
	private SharedPreferences mPreferences;
	private boolean m_Login;
	private String mSession;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mVideoImage1 = (VideoView) findViewById(R.id.imageView1);
		mVideoImage2 = (VideoView) findViewById(R.id.imageView2);
		mBtnSound1 = (Button) findViewById(R.id.btnSound1);
		mBtnSound2 = (Button) findViewById(R.id.btnSound2);
		mBtnTalkStart = (Button) findViewById(R.id.btnTalkStart);
		mBtnTalkStop = (Button) findViewById(R.id.btnTalkStop);
		mEtDevIdno = (EditText) findViewById(R.id.ed_idno);
		mEtServer = (EditText) findViewById(R.id.ed_server);
		mBtnStart = (Button) findViewById(R.id.btnStart);
		mBtnStop = (Button) findViewById(R.id.btnStop);
		mBtnRecord = (Button) findViewById(R.id.btnRecord);
		mBtnMonitorStart =(Button) findViewById(R.id.btnMonitorStart);
		mBtnMonitorStop = (Button) findViewById(R.id.btnMonitorStop);
		PlayClickListener playClickListen = new PlayClickListener();
		mBtnStart.setOnClickListener(playClickListen);
		mBtnStop.setOnClickListener(playClickListen);
		mBtnRecord.setOnClickListener(playClickListen);
		mBtnSound1.setOnClickListener(playClickListen);
		mBtnSound2.setOnClickListener(playClickListen);
		mBtnTalkStart.setOnClickListener(playClickListen);
		mBtnTalkStop.setOnClickListener(playClickListen);
		
		mBtnMonitorStart.setOnClickListener(playClickListen);
		mBtnMonitorStop.setOnClickListener(playClickListen);
		
		mRealPlay1 = new RealPlay(this);
		mRealPlay2 = new RealPlay(this);
		mRealPlay1.setVideoView(mVideoImage1);
		mRealPlay2.setVideoView(mVideoImage2);
		// 如果服务器做了限制 必须要用户登录才能看视频 调用一下接口
		String url = "http://218.100.210.10:8088/LoginAction_loginMobile.action?update=gViewerAndroid&server=login&userAccount=FYD0001&password=000000";
//		AsyncHttpClient.sendRequest(this, url, null, new LoginResponseListener());
		Retrofit retrofit = new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.baseUrl("http://218.100.210.10:8088/")
				.build();
		RequestInterface requestInterface = retrofit.create(RequestInterface.class);
		Observable<Translations> observable = requestInterface.getCall();
		observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Translations>() {



			@Override
			public void onSubscribe(Disposable d) {

			}

			@Override
			public void onNext(Translations translations) {
				if (!MainActivity.this.isFinishing()) {
					int result = -1;
					try {
						result = translations.getResult();
						mSession = translations.getJSESSIONID();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if(result == 0){
						m_Login = true;
						NetClient.SetSession(mSession);
						Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
					}


				} else {
					//logger.log(Level.INFO,"LoginActivity.LoginResponseListener.onFailure() LoginActivity.this.isFinishing()");
				}
				Log.e("111", "onNext: " + translations );
			}

			@Override
			public void onError(Throwable e) {
				e.printStackTrace();
			}

			@Override
			public void onComplete() {

			}
		});
		mPreferences = getSharedPreferences("com.cmsv6demo", 0);
        String server = mPreferences.getString("Server", "192.168.1.230");
        server = "47.105.162.81";
        mEtServer.setText(server);
        String devIdno = mPreferences.getString("DevIDNO", "50003");
        devIdno = "80017526";
       	mEtDevIdno.setText(devIdno);       
		NetClient.Initialize("/mnt/sdcard/");
		
		if (!updateServer()) {
			return ;
		}
		StartAV();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		StopAV();
		NetClient.UnInitialize();
		super.onDestroy();
	}
	
	protected boolean updateServer() {
		mServer = this.mEtServer.getText().toString().trim();
    	mDevIdno = this.mEtDevIdno.getText().toString().trim();
    	
    	if (mServer.isEmpty() || mDevIdno.isEmpty()) {
    		Toast.makeText(getApplicationContext(), "server or devidno is empty", Toast.LENGTH_SHORT).show(); 
    		return false;
    	}
    	
    	SharedPreferences.Editor localEditor = mPreferences.edit();
		localEditor.putString("Server", mServer);
		localEditor.putString("DevIDNO", mDevIdno);
		localEditor.commit();
    	
		NetClient.SetDirSvr(mServer, mServer, 6605, 0);
		m_Login = true;
		return true;
	}
	
	protected void StartAV() {
		if (!mIsStartAV) {
			
			///直连播放
//			mRealPlay1.setLanInfo(mServer, 6688);
//			mRealPlay2.setLanInfo(mServer, 6688);
			mRealPlay1.setViewInfo(mDevIdno, mDevIdno, 0, "CH1");
	       	mRealPlay2.setViewInfo(mDevIdno, mDevIdno, 1, "CH2");
	      //是否铺满画面 true 是  false否
	       	mRealPlay1.setVideoBmpExtend(false);
	       	mRealPlay2.setVideoBmpExtend(false);
	       	
	       	mRealPlay1.StartAV(false, true);
	       	mRealPlay2.StartAV(false, true);
	       	mIsStartAV = true;
		}
	}
	
	protected void StopAV() {
//		if (mIsStartAV) {
//			mRealPlay1.StopAV();
//			mRealPlay2.StopAV();
//			mIsStartAV = false;
//		}
		mRealPlay1.StopAV();
		mRealPlay2.StopAV();
		mIsStartAV = false;
	}
	
	protected void onSound1() {
		mRealPlay1.playSound();
		mRealPlay2.stopSound();
	}
	
	protected void onSound2() {
		mRealPlay1.stopSound();
		mRealPlay2.playSound();
	}
	
	protected void onTalkStart() {
		if (mTalkback == null) {
			if (!updateServer()) {
				return ;
			}
			
			mTalkback = new Talkback();
			mTalkback.startTalkback(mDevIdno, 0);
		}
	}
	
	protected void onTalkStop() {
		if (mTalkback != null) {
			mTalkback.stopTalkback();
			mTalkback = null;
		}
	}
	
	protected void onMonitorStart() {
		if (mMonitor == null) {
			if (!updateServer()) {
				return ;
			}
			
			mMonitor = new Monitor();
			mMonitor.startMonitor(mDevIdno, 0);
		}
	}
	
	protected void onMonitorStop() {
		if (mMonitor != null) {
			mMonitor.stopMonitor();
			mMonitor = null;
		}
	}
	
	final class PlayClickListener implements OnClickListener {
		public void onClick(View v) {
			if (v.equals(mBtnStart)) {
				StartAV();
			} else if (v.equals(mBtnStop)) {
				StopAV();
			} else if (v.equals(mBtnRecord)) {
				if(m_Login){
					Intent intent = new Intent(); 
					String devIdno = mEtDevIdno.getText().toString().trim();
					intent.putExtra("DevIDNO", devIdno); 
					intent.setClass(MainActivity.this, RecordActivity.class);
					startActivityForResult(intent, 0);
				}
				StopAV();
				
				
			} else if (v.equals(mBtnSound1)) {
				onSound1();
			} else if (v.equals(mBtnSound2)) {
				onSound2();
			} else if (v.equals(mBtnTalkStart)) {
				onTalkStart();
			} else if (v.equals(mBtnTalkStop)) {
				onTalkStop();
			}
			else if (v.equals(mBtnMonitorStart)) {
				onMonitorStart();
			} else if (v.equals(mBtnMonitorStop)) {
				onMonitorStop();
			}
		}
	}
	
	final class LoginResponseListener extends AbstractAsyncResponseListener {
		@Override
		protected void onFailure(Throwable e) {
			if (!MainActivity.this.isFinishing()) {
//				hideWaitDialog();
//				ToastUtil.showToast(R.string.login_server_error);
			}
		}

		@Override
		protected void onSuccess(JSONObject jsonObject) {
			if (!MainActivity.this.isFinishing()) {
				int result = -1;							
				try {
					result = jsonObject.getInt("result");
					mSession = jsonObject.getString("JSESSIONID");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(result == 0){
					m_Login = true;
					NetClient.SetSession(mSession);
					Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
				}

				
			} else {
				//logger.log(Level.INFO,"LoginActivity.LoginResponseListener.onFailure() LoginActivity.this.isFinishing()");
			}
		}
	}
}
