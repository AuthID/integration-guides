![Ipsidy](../../images/ipsidy.png)
# Proof/Verified Web - FaceLok Web Container Configuration

FaceLok Web container provides ability to configure some of the behavior settings when running “On-Premises”. Below is the list of supported options listed as environment variables.

| Variable Name | Description | Default Value |
| ------------- | ----------- | ------------- |
| UsePathBase | For running behind reverse-proxies that do not support URL-rewriting and pass prefixed request path use `UsePathBase` parameter so that application can extract and re-apply the prefix when serving requests. | |
| IDLive__Url | ID R&D’s IDLive™ Face integration is enabled by specifying URL to the running service instance. | |
| IDLive__Threshold | Threshold value that allows IDLive Face image verification to pass. | 0.80 |
| Redis__Config | Enables Redis session persistence.<br> By default, service stores session information in (local) memory hence if load balancer is utilized it needs to be configured to ensure "session stickiness" - each session created via POST should always be served by the same service instance.<br> To allow more flexibility for balancer configuration service supports storage of session information in the Redis store and allowing same session to be served freely by any container instance.<br> At minimum set variable to the host name of the Redis host and (possible) authentication values:<br> Redis__Config="redis_host:6379,password=secret"<br> For more detailed configuration string options check https://stackexchange.github.io/StackExchange.Redis/Configuration article.
| Redis__KeyPrefix | Configures Redis key prefix that container should use when storing values in the DB. | “flwa:” |
| Redis__DbIndex | Configures Redis database index to use. By default (-1) service will not use indexed database functionality and will access default one. | -1 |
| Limits__MaxSessionsPerSourceOperationId | Enables session limiting mechanism based on Redis OperationId tracking. <br> If the Redis is enabled (via Redis__Config), and Limits__MaxSessionsPerSourceOperationId is set non-zero value, each POST of api/LivenessTest requires client to pass SourceOperationId field in the body JSON.<br> Service will then check if Redis DB contains "OperationId-{SourceOperationId}" (no prefix is used) hash key and only allow further processing when such key exists.<br> Additionally, service will increment "flwa-sessions" field in the specified (hash) key and only continue if total count of sessions is below Limits__MaxSessionsPerSourceOperationId value. | 0 |
| Limits__MaxSessionTime | Specifies number of seconds after which session is automatically cleaned-up. | 600 |
| AIConfig__FaceThreshold | For experimental/development purposes these recognition tuning parameters are available for changing during start-up. Normally these should not be changed. | 0.5 |
| AIConfig__SmileThreshold | | 0.8 |
| AIConfig__BlinkThreshold | | 0.7 |
| AIConfig__SamplingWindow | | 120 |
| AIConfig__MinFaceSize | | 250 |
| AIConfig__ SmileDelta | | 1 |
| Tracker__ExpScale | | 4 |

#

[Back to Main Page](../README.md)