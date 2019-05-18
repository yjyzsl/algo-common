package com.shilei.algo.stack;

/**
 *
 * @Description: 基于栈实现浏览器的前进后退
 * @Author: shilei
 * @Date: 2019/5/17 17:47
 **/
public class SampleBrowserStack {

    /**
     * 当前页面
     */
    private String cuurentPage;
    /**
     * 存放后退页面栈
     */
    private BaseStackOnLinkedList<String> backStack;
    /**
     * 存放前进页面栈
     */
    private BaseStackOnLinkedList<String> forwarStack;

    public SampleBrowserStack() {
        this.backStack = new BaseStackOnLinkedList<>();
        this.forwarStack = new BaseStackOnLinkedList<>();
    }

    public BaseStackOnLinkedList<String> getBackStack() {
        return backStack;
    }

    public BaseStackOnLinkedList<String> getForwarStack() {
        return forwarStack;
    }

    /**
     * 是否可以后退
     * @return
     */
    public boolean canGoBack(){
        return backStack.getSize() > 0 ? true : false;
    }

    /**
     * 是否可以前进
     * @return
     */
    public boolean canGoForward(){
        return forwarStack.getSize() > 0 ? true : false;
    }

    /**
     * 后退
     */
    public String goBack(){
        String page;
        if(canGoBack()){
            // 1.从后退栈中弹出一个
            page = backStack.pop();
            // 2.将弹出来的页面村粗到前进栈中
            forwarStack.push(page);
            showPage(page," back ");
            return page;
        }
        page = null;
        System.out.println("Cannot go back , no pages behind.");
        return page;
    }

    /**
     * 前进
     * @return
     */
    public String goForward(){
        String page;
        if(canGoForward()){
            // 1.从前进栈中弹出页面
            page = forwarStack.pop();
            // 2.弹出的页面存放到后退栈中
            backStack.push(page);
            showPage(page," forward ");
            return page;
        }
        page = null;
        System.out.println("Cannot go forward , no pages ahind.");
        return page;
    }

    /**
     * 打开新页面
     * @return
     */
    public String open(String page){
        // 1.新打开的页面新存放到后退栈中
        backStack.push(page);
        // 2.前进栈则需要清空，因为新页面不会有前进页面
        forwarStack.clear();
        showPage(page," open ");
        return page;
    }

    public void showPage(String page,String msg){
        System.out.println(page + msg);
        this.cuurentPage = page;
    }

    public static void main(String[] args) {
        SampleBrowserStack sampleBrowserStack = new SampleBrowserStack();
        sampleBrowserStack.open("1");
        sampleBrowserStack.open("2");
        sampleBrowserStack.open("3");
        sampleBrowserStack.open("4");

        sampleBrowserStack.getBackStack().print();
        sampleBrowserStack.getForwarStack().print();

        System.out.println("======================");
        sampleBrowserStack.goBack();
        sampleBrowserStack.getBackStack().print();
        sampleBrowserStack.getForwarStack().print();

        System.out.println("======================");
        sampleBrowserStack.goBack();
        sampleBrowserStack.getBackStack().print();
        sampleBrowserStack.getForwarStack().print();

        System.out.println("======================");
        sampleBrowserStack.goForward();
        sampleBrowserStack.getBackStack().print();
        sampleBrowserStack.getForwarStack().print();

        System.out.println("======================");
        sampleBrowserStack.open("5");
        sampleBrowserStack.getBackStack().print();
        sampleBrowserStack.getForwarStack().print();

        sampleBrowserStack.goForward();
        sampleBrowserStack.goForward();

        sampleBrowserStack.goBack();
        sampleBrowserStack.goBack();
        sampleBrowserStack.goBack();
        sampleBrowserStack.goBack();
        sampleBrowserStack.goBack();
        sampleBrowserStack.goBack();

        sampleBrowserStack.getBackStack().print();
        sampleBrowserStack.getForwarStack().print();


    }


}
