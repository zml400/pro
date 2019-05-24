package com.pro.entity;

public class Result {
    private int code;
    private String message;
    private int pagesTotal;
    private int currentPage;
    private Object object;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPagesTotal() {
        return pagesTotal;
    }

    public void setPagesTotal(int pagesTotal) {
        this.pagesTotal = pagesTotal;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", pagesTotal=" + pagesTotal +
                ", currentPage=" + currentPage +
                ", object=" + object +
                '}';
    }
}
