package com.example.myproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class Called extends Activity implements OnItemClickListener {
	public static String categoryfor;
    private ListView mRssListView;
    private NewsFeedParser mNewsFeeder;
    private List<RSSFeed> mRssFeedList;
    private RssAdapter mRssAdap;

    private   String TOPSTORIES;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rss_feed_view);
        Bundle xyz=getIntent().getExtras();
        TOPSTORIES=xyz.getString("key");
        mRssListView = (ListView) findViewById(R.id.rss_list_view);
        mRssFeedList = new ArrayList<RSSFeed>();
        new DoRssFeedTask().execute(TOPSTORIES);
        mRssListView.setOnItemClickListener(this);
    }

    private class RssAdapter extends ArrayAdapter<RSSFeed> {
        private List<RSSFeed> rssFeedLst;

        public RssAdapter(Context context, int textViewResourceId, List<RSSFeed> rssFeedLst) {
            super(context, textViewResourceId, rssFeedLst);
            this.rssFeedLst = rssFeedLst;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;
            RssHolder rssHolder = null;
            if (convertView == null) {
                view = View.inflate(Called.this, R.layout.rss_list_item, null);
                rssHolder = new RssHolder();
                rssHolder.rssTitleView = (TextView) view.findViewById(R.id.rss_title_view);
                rssHolder.image1 = (ImageView) view.findViewById(R.id.imageView1);
           
                view.setTag(rssHolder);
            } else {
                rssHolder = (RssHolder) view.getTag();
            }
            RSSFeed rssFeed = rssFeedLst.get(position);
     
            String a=rssFeed.getLink();
            String b=rssFeed.getTitle();
            rssHolder.rssTitleView.setText(
                    		Html.fromHtml(
                                    "<a style='text-decoration:none'href=\""+a+"\">"+b+"</a>" ));
            rssHolder.rssTitleView.setMovementMethod(LinkMovementMethod.getInstance());
            rssHolder.rssTitleView.setLinkTextColor(Color.BLACK);
            stripUnderlines(rssHolder.rssTitleView);
            Random r = new Random();
			int i1=r.nextInt(15) + 1;
			switch(i1)
			{
			
			case 1:
				 rssHolder.image1.setImageResource(R.drawable.ab1);
				 break;
			case 2:
				 rssHolder.image1.setImageResource(R.drawable.ab2);
				 break;
			case 3:
				 rssHolder.image1.setImageResource(R.drawable.b2);
				 break;
			case 4:
				 rssHolder.image1.setImageResource(R.drawable.ab4);
				 break;
			case 5:
				 rssHolder.image1.setImageResource(R.drawable.ab5);
				 break;
			case 6:
				 rssHolder.image1.setImageResource(R.drawable.ab6);
				 break;
			case 7:
				 rssHolder.image1.setImageResource(R.drawable.ab7);
				 break;
			case 8:
				 rssHolder.image1.setImageResource(R.drawable.ab8);
				 break;
			case 9:
				 rssHolder.image1.setImageResource(R.drawable.icongray);
				 break;
			case 10:
				 rssHolder.image1.setImageResource(R.drawable.j1);
				 break;	 
			case 11:
				 rssHolder.image1.setImageResource(R.drawable.logo);
				 break;	
			case 12:
				 rssHolder.image1.setImageResource(R.drawable.j2);
				 break;
			case 13:
				 rssHolder.image1.setImageResource(R.drawable.j3);
				 break;
			case 14:
				 rssHolder.image1.setImageResource(R.drawable.j4);
				 break;
			case 15:
				 rssHolder.image1.setImageResource(R.drawable.j5);
				 break;	 
				 
			}
            
            return view;
        }
    }

    static class RssHolder {
        public TextView rssTitleView;
        public TextView textview;
        public ImageView image1;
    }

    public class DoRssFeedTask extends AsyncTask<String, Void, List<RSSFeed>> {
        ProgressDialog prog;
        String jsonStr = null;
        Handler innerHandler;

        @Override
        protected void onPreExecute() {
            prog = new ProgressDialog(Called.this);
            prog.setMessage("Loading....");
            prog.show();
        }

        @Override
        protected List<RSSFeed> doInBackground(String... params) {
            for (String urlVal : params) {
                mNewsFeeder = new NewsFeedParser(urlVal);
            }
            mRssFeedList = mNewsFeeder.parse();
            return mRssFeedList;
        }

        @Override
        protected void onPostExecute(List<RSSFeed> result) {
            prog.dismiss();
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    mRssAdap = new RssAdapter(Called.this, R.layout.rss_list_item,
                            mRssFeedList);
                    int count = mRssAdap.getCount();
                    if (count != 0 && mRssAdap != null) {
                        mRssListView.setAdapter(mRssAdap);
                    }
                }
            });
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
    class URLSpanNoUnderline extends URLSpan {
        public URLSpanNoUnderline(String url) {
            super(url);
        }
        @Override public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
        }
    }
    private void stripUnderlines(TextView textView) {
        Spannable s = (Spannable)textView.getText();
        URLSpan[] spans = s.getSpans(0, s.length(), URLSpan.class);
        for (URLSpan span: spans) {
            int start = s.getSpanStart(span);
            int end = s.getSpanEnd(span);
            s.removeSpan(span);
            span = new URLSpanNoUnderline(span.getURL());
            s.setSpan(span, start, end, 0);
        }
}
    @Override
    public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
    }
}
