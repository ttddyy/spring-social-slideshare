# spring-social-slideshare

Spring Social provider module for SlideShare API.


# Project Status

Currently, only `SlideshowTemplate`(slideshow related operations) is implemented.  
If you need other operations(user, leads, campaign, etc) or implementation of Spring Social's _Service Provider 
Connect Framework_ (such as `ApiAdapter`, `ConnectionFactory`, `ServiceProvider`, etc), please ping me or [create an 
issue on github](https://github.com/ttddyy/spring-social-slideshare/issues). 
I will implement them if there is a need.

I'm [@ttddyy](https://twitter.com/ttddyy) on twitter if you want to ping me. :)   


## key features

- `SlideshowTemplate` to interact with SlideShare slideshow related operations in java 


## library versions

| spring-social-slideshare | spring-social | spring-framework |                                note |
| ------------------------:| -------------:| ----------------:| ------------------------------------| 
|           1.0.0-SNAPSHOT | 1.1.0.RELEASE |    4.1.2.RELEASE |   work with later version of spring |

## how to get


```xml
  <dependency>
      <groupId>net.ttddyy</groupId>
      <artifactId>spring-social-slideshare</artifactId>
      <version>1.0.0-SNAPSHOT</version>
  </dependency>
```
\* not uploaded maven yet... please compile for now.

# Usage

```java
  SlideShare slideShare = new SlideShareTemplate("api_key", "shared_secret");
  SlideshowOperations slideshowOperations = slideShare.slideshowOperations();
  Slideshow slideshow = slideshowOperations.getSlideshow("0123456", ...);
  // you can get/search/edit/delete/upload slideshows
```

# TODO


