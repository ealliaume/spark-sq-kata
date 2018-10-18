# Spark SQL Kata in Scala

In this Kata you will play with Spark 2.x SQL DSL.
 
It is been split in steps, each step will allow you do play with basics functions/operation of Spark: read, count, filtering, joins, etc.

The dataset used for this kata is a list of Android Applications, it has been copied from [Kagle](https://www.kaggle.com/lava18/google-play-store-apps) 

## Prerequisites

1. Have SBT and Scala installed

## Part1: Play with SparkSQL locally

1. Clone this repository

2. Each step is described and tested in the MovieKataTest class, your goal is to make those tests green

3. If you want to cheat and see the solution, there is  git branch for that :) 

This Kata should take approximately 45 minutes, no previous Spark knowledge needed.

## Part2: Deploy a spark Application on AWS EMR

This Kata should take approximately 30 minutes, no previous EMR knowledge needed.

### A. Look at the code

1. Clone this repository

2. Take a look to NYCPoliceKata.scala files and tests

3. Take a look the the small version of the dataset: Parking_Violations_small.csv

### B. Create The Cluster

1. On the AWS Console, create an EMR cluster
- prefix the name of the cluster by your name
- Launch Mode: Cluster
- Release: emr-5.17.0
- Applications: Spark: Spark 2.3.1 on Hadoop 2.8.4 YARN with Ganglia 3.7.2 and Zeppelin 0.7.3
- Instance type: m3.xlarge
- Number of instances: 3
- EC2 Key Pair: <your key pair>
- EMR Role: EMR_DefaultRole
- EC2 Instance Profile Role: EMR_EC2_DefaultRole

2. Wait for the cluster to be "Waiting, Cluster Ready" (about 10 minutes)

### C. Run the Spark Application

1. Upload the jar of you application on s3
- customise and use the script: build_and_upload-to-s3.sh

2. Add a "Step" to the cluster to start your application
- Step Type: Custom JAR
- JAR Location: command-runner.jar
- Arguments: spark-submit --deploy-mode cluster --class au.com.octo.NYCPoliceKata s3://era-bigdata-demo/apps/spark-kata-<USER>.jar --app-name nyc-<USER>
(This job takes about 9 minutes to run)

3. Take a look to the web console
- Summary -> Enable Web Connection
- Summary -> Spark History Server
- Summary -> Resources Managers

4. Try to find the result of the "ds.show()" in the logs using the Hadopp Console and S3 Logs

### D. Run the Spark Application

1. Terminate your cluster

2. Try to find the result of the "ds.show()" in the logs using the Hadopp Console and S3 Logs


Good luck!