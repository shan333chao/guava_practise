package com.sunday.servant;

import com.sunday.service.Service;

import java.util.ServiceLoader;

/**
 * Created by HenDiao on 2017/10/13.
 */
public class ServiceInvoker {
    public static void main(String[] args) {
        ServiceLoader<Service>  serviceLoader=ServiceLoader.load(Service.class);
        for (Service service :serviceLoader){
            service.show();
        }
    }
}
