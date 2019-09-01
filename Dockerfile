FROM openjdk:8

ADD target/HurriyetEmlakStudyCases-1.0-SNAPSHOT-tests.jar HurriyetEmlakStudyCases.jar

ADD target/test-classes/testng.xml testng.xml

ADD /lib/ /lib/

ENTRYPOINT java -cp /lib/testng-7.0.0.jar:/lib/jcommander-1.72.jar:HurriyetEmlakStudyCases.jar:/lib/* org.testng.TestNG testng.xml