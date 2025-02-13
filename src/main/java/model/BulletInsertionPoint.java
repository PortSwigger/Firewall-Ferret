package model;

import burp.api.montoya.core.ByteArray;
import burp.api.montoya.core.Range;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.logging.Logging;
import burp.api.montoya.scanner.audit.insertionpoint.AuditInsertionPoint;
import model.creators.BulletFactory;
import model.creators.RequestBuilder;

import java.util.List;

////////////////////////////////////////
// CLASS BulletInsertionPoint
////////////////////////////////////////
public class BulletInsertionPoint implements AuditInsertionPoint{

//-----------------------------------------------------------------------------
public BulletInsertionPoint(HttpRequest request, int kilobytes, Logging logging){
  _request   = request;
  _baseValue = BulletFactory.bullet(kilobytes * 1024);
  _name      = String.valueOf(kilobytes).concat("kb Bullet Insertion Point");
  _logging   = logging;
}

//-----------------------------------------------------------------------------
@Override
public String name(){
  return _name;
}

//-----------------------------------------------------------------------------
@Override
public String baseValue(){
  return _baseValue;
}

//-----------------------------------------------------------------------------
@Override
public HttpRequest buildHttpRequestWithPayload(ByteArray payload){
  HttpRequest updatedReq = null;
  try {
    updatedReq = RequestBuilder.build(_request, _baseValue.concat(payload.toString()));
  }
  catch(UnsupportedOperationException e) {
    _logging.raiseErrorEvent(e.getMessage());
    _logging.logToError(e);
  }
  
  return updatedReq;
}

//-----------------------------------------------------------------------------
@Override
public List<Range> issueHighlights(ByteArray payload){
  return List.of();
}

private final String      _name;
private final HttpRequest _request;
private final String      _baseValue;
private final Logging     _logging;
}
////////////////////////////////////////
// END CLASS BulletInsertionPoint
////////////////////////////////////////