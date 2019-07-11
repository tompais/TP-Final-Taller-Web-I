#!/bin/sh
cd $TRAVIS_BUILD_DIR/'Codigo Fuente'/CineApp
sbt ++$TRAVIS_SCALA_VERSION package