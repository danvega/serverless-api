# AWS Lambda + Spring Boot 3

Before we get started, let's talk about why you might want to use AWS Lambda and Spring Boot 3. You are probably used
to packaging your applications as a JAR and deploying them to a server. You might even take that application and 
containerize it using Docker and deploy it to Kubernetes.

When you manage your own server you have to think about things like patching, security, and scaling. When you deploy an
application to a server, you have to consider how much traffic your application will receive. If you have a server that is
running at 100% utilization, you might need to scale up to handle the load. If you have a server that is running at 10% utilization,
you might want to scale down to save money.

AWS Lambda is a serverless compute service that lets you run code without provisioning or managing servers. You pay only for the 
compute time you consume. This means that my personal project that I am working on that isn't going to receive a ton of traffic 
it is an ideal candidate for AWS Lambda. Any applications with variable traffic or that can scale to zero are great candidates for
this use case.

Most people understand this concept for deploying functions as a service (FaaS) but in this tutorial we are going to deploy an
entire Spring Boot REST API. If you look at the [AWS Pricing Page](https://aws.amazon.com/lambda/pricing/) you will see that
The AWS Lambda free tier includes one million free requests per month and 400,000 GB-seconds of compute time per month. This
means that for most of the side projects I am working on I can deploy them to AWS Lambda and not have to pay anything.

## Prerequisites

- Java 17
- Spring Boot 3.1 
- Following the Quick Start https://github.com/awslabs/aws-serverless-java-container/wiki/Quick-start---Spring-Boot3

Thank you to [Mark Sailes](https://twitter.com/MarkSailes3) for all of his help with this tutorial.

## Agenda

In this tutorial you will create a new Spring Boot 3 application, create a CRUD REST API and then package it and deploy
it to AWS Lambda. 

- start.spring.io
  - spring boot 3.1.0
  - maven / java
  - web, webflux
- Build CRUD REST API
  - post package
  - PostController
  - Post Record
  - PostNotFoundException
- Blog Posts Loader
  - JsonPlaceholderService
- Test locally using http client before packaging for production

### Maven Archetype

A Maven Archetype is a template project that you can use to generate a new project. The AWS Serverless Spring Boot 3 
archetype is a Maven project that you can use to create a new Spring Boot 3 project that includes the AWS Serverless Container
for Spring Boot 3. This will also generate the correct Maven profile needed to generate the AWS Lambda deployment package.

If you're using IntelliJ IDEA, you can use the Maven Archetype to create a new project.

`aws-serverless-springboot3-archetype`

If you have Maven installed, you can use the following command to generate a new project:

```bash
mvn archetype:generate -DgroupId=my.service -DartifactId=my-service -Dversion=1.0-SNAPSHOT \
       -DarchetypeGroupId=com.amazonaws.serverless.archetypes \
       -DarchetypeArtifactId=aws-serverless-springboot3-archetype \
       -DarchetypeVersion=2.0.0-M1
```

## AWS Resources

To deploy your application to AWS Lambda, you will need to create the following AWS resources:

- AWS Lambda
- API Gateway

You can do this manually using the AWS Console, or you can use code to create the resources. In this tutorial, you will use
the AWS Console, but I am leaving you some resources if you want to dive into SAM or CDK.

### AWS SAM

What is SAM? SAM is the Serverless Application Model. It's an open-source framework that you can use to build serverless
applications on AWS. It provides a simplified way of defining the AWS resources (infrastructure as code) needed to run your application. It also 
provides a command line interface (CLI) that you can use to package and deploy your application to AWS.

SAM uses a template file (`template.yaml`) to define your application. The template file defines the AWS resources needed to run your application. You might
want to review that template file before you deploy your application.

mvn clean package 
sam deploy --guided

https://aws.amazon.com/serverless/sam/


### AWS CDK

The [AWS Cloud Development Kit (AWS CDK)](https://aws.amazon.com/cdk/) is an open source software development framework to define your cloud application resources using familiar programming languages. Because the AWS CDK enables you to define your infrastructure in regular programming languages, you can also write automated unit tests for your infrastructure code, just like you do for your application code.


