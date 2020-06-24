#!/bin/bash


echo 'Will change the version in pom.xml files...'

branch=$(git rev-parse --abbrev-ref HEAD)
version="$branch"
version="$(echo $version | sed 's/bugfix\///g')"
version="$(echo $version | sed 's/feature\///g')"
version="$(echo $version | sed 's/release\///g')"
version=$(echo "$version" | awk '{print toupper($0)}')
mvn clean versions:set -DgenerateBackupPoms=false -DnewVersion="$version"
mvn clean install deploy
mvn clean
echo 'Changed version in pom.xml files to= '$version
exit 0
