package io.richardradics.test.nytimes.articles.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.richardradics.test.nytimes.R;
import io.richardradics.test.nytimes.core.presentation.BaseActivity;

public class ArticleDetailActivity extends BaseActivity {

    public static String ARG_URL = "ARG_URL";

    @BindView(R.id.webview)
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        ButterKnife.bind(this);

        final String url = getIntent().getStringExtra(ARG_URL);

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}
