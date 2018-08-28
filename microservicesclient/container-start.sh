#!/bin/sh

echo "*** NOTE: Executing container-start.sh"

##---------------------------------------------------------------------------##
set APK_ADD="bash curl httpie"
set APK_DEL="bash curl"


##---------------------------------------------------------------------------##
# Update OS:
echo "*** NOTE: Updating and upgrading OS packages"
apk update && \
apk upgrade && \
apk add --no-cache $APK_ADD


##---------------------------------------------------------------------------##
echo "*** NOTE: Clean up files not needed for running the container..."
rm -rf \
      /usr/share/man/* \
      /usr/includes/* \
      /var/cache/apk/* \
      /root/.npm/* \
      /usr/lib/node_modules/npm/man/* \
      /usr/lib/node_modules/npm/doc/* \
      /usr/lib/node_modules/npm/html/* \
      /usr/lib/node_modules/npm/scripts/*



##---------------------------------------------------------------------------##
echo "*** NOTE: Clean up USERS not needed for running the container..."

deluser --remove-home daemon
deluser --remove-home adm
deluser --remove-home lp
deluser --remove-home sync
deluser --remove-home shutdown
deluser --remove-home halt
deluser --remove-home postmaster
deluser --remove-home cyrus
deluser --remove-home mail
deluser --remove-home news
deluser --remove-home uucp
deluser --remove-home operator
deluser --remove-home man
deluser --remove-home cron
deluser --remove-home ftp
deluser --remove-home sshd
deluser --remove-home at
deluser --remove-home squid
deluser --remove-home xfs
deluser --remove-home games
deluser --remove-home postgres
deluser --remove-home vpopmail
deluser --remove-home ntp
deluser --remove-home smmsp
deluser --remove-home guest


#apk del $APK_DEL


##---------------------------------------------------------------------------##
## END
##---------------------------------------------------------------------------##
