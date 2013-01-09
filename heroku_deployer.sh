#The MIT License
#
#Copyright (c) 2012, Daniel Petisme
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in
# all copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
# THE SOFTWARE.

#Disclaimer: This script is mainly inspired from the following
# http://neilmiddleton.com/deploying-to-heroku-from-travis-ci/. It explains how to use Travis CI (https://travis-ci.org/)
# to deploy on Heroku platforms.

#Arguments
# $1: your Heroku API token (see: https://devcenter.heroku.com/articles/authentication#authenticating-with-the-api-token)
# $2: your Heroku Application name (for instancem: rocky-lowlands-7979)

#TODO Add a CLI checking

#HEROKU API Token export because the authentication mechanism search for this environment variable
export HEROKU_API_KEY=133596e7adb8cd7353abb843c80293f48e7a4a7f

#Heroku installation mainly based on the official script (https://toolbelt.heroku.com/install.sh)
HEROKU_HOME=`pwd`/heroku
pushd .
rm -rf ${HEROKU_HOME}
mkdir -p ${HEROKU_HOME}
cd ${HEROKU_HOME}
wget -qO- http://assets.heroku.com.s3.amazonaws.com/heroku-client/heroku-client.tgz | tar xz
mv heroku-client/* .
rm -rf heroku-client
export PATH=${PATH}:${HEROKU_HOME}/bin
popd

#The SSH Keys nightmare
rm -rf ~/.ssh/*

#More details about the following lines (http://linuxcommando.blogspot.fr/2008/10/how-to-disable-ssh-host-key-checking.html)
touch ~/.ssh/config && chmod 700 ~/.ssh/config
echo "Host heroku.com
   StrictHostKeyChecking no
   CheckHostIP no
   UserKnownHostsFile=/dev/null" > ~/.ssh/config
    

#Import the SSH public key after removing the old one
heroku keys:clear
yes | heroku keys:add


#We define the Heroku application git remote repository
heroku git:remote -a pile-qui-deborde

#Finally, we can deploy using Git
#Heroku Git-based deployment (https://devcenter.heroku.com/articles/git)
yes | git push heroku master
