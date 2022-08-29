package com.example.servicebestpractice;

public interface DownloadListener {
    void onProcess(int process);

    void onPause();

    void onSuccess();

    void onFailed();

    void onCanceled();
}
