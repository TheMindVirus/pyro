/*
 * Copyright (c) 2015.
 * Created by MrTJP.
 * All rights reserved.
 */
package mrtjp.core.fx

import mrtjp.core.fx.particles.CoreParticle

import scala.collection.mutable.{Seq => MSeq}

class GroupAction extends ParticleAction {
    var actions = MSeq[ParticleAction]()

    override def tickLife(): AnyVal = {
        super.tickLife()
        actions.foreach(_.tickLife())
    }

    override def runOn(p: CoreParticle, frame: Float): AnyVal = {
        super.runOn(p, frame)

        actions.foreach { a =>
            if (!a.isFinished) {
                a.runOn(p, frame)
            }
        }

        if (actions.forall(_.isFinished)) {
            isFinished = true
        }
    }

    override def operate(p: CoreParticle, time: Double): AnyVal = {}

    override def compile(p: CoreParticle): AnyVal = {
        super.compile(p)
        actions.foreach(_.compile(p))
    }

    override def reset(): AnyVal = {
        super.reset()
        actions.foreach(_.reset())
    }

    override def copy = ParticleAction.group(actions.map(_.copy).toList: _*)
}
