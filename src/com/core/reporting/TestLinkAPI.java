package com.core.reporting;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import com.core.utils.Constants;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class TestLinkAPI {

    public static final String DEV_KEY = Constants.TESTLINK_DEV_KEY;
    public static final String API_URL = Constants.TESTLINK_API_URL;

    public static void setStatus(int testCaseId, int testPlanId, ExecutionStatus status) {
        try {
            System.out.println("Setting status: " + testCaseId + " " + testPlanId + " " + status);
            XmlRpcClient rpcClient;
            XmlRpcClientConfigImpl config;

            config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL(API_URL));
            rpcClient = new XmlRpcClient();
            rpcClient.setConfig(config);

            List<Object> params = new ArrayList<Object>();
            Map<String, Object> executionData = new Hashtable<String, Object>();
            executionData.put("devKey", DEV_KEY);
            executionData.put("tcid", testCaseId);
            executionData.put("tpid", testPlanId);
            executionData.put("status", status.toString());
            params.add(executionData);

            rpcClient.execute("tl.reportTCResult", params);
        } catch (MalformedURLException e) {
            System.out.println("[TESTLINK ERROR] Incorrect testlink API URL!");
        } catch (Exception e) {
            System.out.println("[TESTLINK ERROR] Error while updating test status!");
        }
    }

}
