package com.person.raft.server.netty;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author huangwenjun
 * @Date 2018年9月3日
 */
public class SyncFuture<T> implements Future<T> {
    // 因为请求和响应是一一对应的，因此初始化CountDownLatch值为1。
    private CountDownLatch latch = new CountDownLatch(1);
    // 需要响应线程设置的响应结果
    private T response;
    // Futrue的请求时间，用于计算Future是否超时
    private long beginTime = System.currentTimeMillis();

    public SyncFuture() {}

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        if (response != null) {
            return true;
        }
        return false;
    }

    // 获取响应结果，直到有结果才返回。
    @Override
    public T get() throws InterruptedException {
        latch.await();
        return this.response;
    }

    // 获取响应结果，直到有结果或者超过指定时间就返回。
    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException {
        if (latch.await(timeout, unit)) {
            return this.response;
        }
        return null;
    }

    // 用于设置响应结果，并且做countDown操作，通知请求线程
    public void setResponse(T response) {
        this.response = response;
        latch.countDown();
    }

    public long getBeginTime() {
        return beginTime;
    }

    @Override
    public Future<T> addListener(GenericFutureListener<? extends Future<? super T>> arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Future<T> addListeners(GenericFutureListener<? extends Future<? super T>>... arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Future<T> await() throws InterruptedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean await(long arg0) throws InterruptedException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean await(long arg0, TimeUnit arg1) throws InterruptedException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Future<T> awaitUninterruptibly() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean awaitUninterruptibly(long arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean awaitUninterruptibly(long arg0, TimeUnit arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Throwable cause() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T getNow() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isCancellable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isSuccess() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Future<T> removeListener(GenericFutureListener<? extends Future<? super T>> arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Future<T> removeListeners(GenericFutureListener<? extends Future<? super T>>... arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Future<T> sync() throws InterruptedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Future<T> syncUninterruptibly() {
        // TODO Auto-generated method stub
        return null;
    }
}
