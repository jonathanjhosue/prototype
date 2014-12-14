package com.company.prototype.service;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

//import test1.Ionn;

import com.company.prototype.model.Member;
import com.company.prototype.util.PrototypeSubProject;

import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class MemberRegistration {

	@Inject
	@PrototypeSubProject
	private Logger log;

	@Inject
	@PrototypeSubProject
	private EntityManager emPrototype;

	@Inject	
	private Event<Member> memberEventSrc;

	public void register(Member member) throws Exception {
		log.info("Registering " + member.getName());
		// new Ionn().maidd();
		emPrototype.persist(member);
		memberEventSrc.fire(member);
	}
}
