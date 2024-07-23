package model;

import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.scanner.audit.insertionpoint.AuditInsertionPoint;
import burp.api.montoya.scanner.audit.insertionpoint.AuditInsertionPointProvider;

import java.util.ArrayList;
import java.util.List;

////////////////////////////////////////
// CLASS InsertPntProvider
////////////////////////////////////////
public class InsertPntProvider implements AuditInsertionPointProvider{


public InsertPntProvider(List<Integer> sizes){bulletSizes = sizes;}

@Override
public List<AuditInsertionPoint> provideInsertionPoints(HttpRequestResponse baseHttpReqResp){
  List<AuditInsertionPoint> insPoints = new ArrayList<>(bulletSizes.size());
  
  for(Integer size : bulletSizes) {
    insPoints.add(new BulletInsertionPoint(baseHttpReqResp.request(), size));
  }
  
  return insPoints;
}

private final List<Integer> bulletSizes;

}
////////////////////////////////////////
// END CLASS InsertPntProvider
////////////////////////////////////////