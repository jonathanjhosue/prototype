package com.company.prototype.util;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;

import org.infinispan.manager.CacheContainer;

@Stateless
public class SimpleCache {
  /*
@Resource(lookup="java:jboss/infinispan/container/prototypeCache")
 private CacheContainer container;
 
 private org.infinispan.Cache<String, String> cache;
 
@PostConstruct
 public void initCache() {
  this.cache = container.getCache();
 }
 
 public String get(String key) {
  return this.cache.get(key);
 }
 
 public void put(String key, String value) {
  this.cache.put(key, value);
 }
 */
}