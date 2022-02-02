# Flexiana Scramble Assignement

To run the backend:

```shell
clj -M:clj:dev
```

and then run in the REPL to start the HTTP server 
```clojure
(start)
```

To run the frontend:
```shell
clj -M:cljs:dev-cljs:shadow-cljs watch app
```

and open http://localhost:8700 in your browser.

## Questionnaire

Q: What is your favorite branching model?

A: Feature branches.

Q: When do you use unit tests and when integration tests?

A: Namespaces that contain only pure functions would need only unit tests, while namespaces that include stateful
components like (a database connection) or their dependents would need integration tests

Q: How can you eliminate or lower null pointer exceptions?

A: Nil punning does a good job of it. When creating a new function and the called functions in its body are nil punning,
then the new function will also have that property. Most of clojure.core and third party libraries do a good job of nil
punning. Another good way when piping expressions are the cond-> and cond->> macros. Of course, you can also use
something like the maybe monad.

Q: What are the general guidelines to build horizontally scalable application?

A: Build small, stateless and idempotent services that can be replicated, and load balanced.

Q: How do you describe clean code?

A: Code that is self-documenting, concise (but not too concise to the point of code golfing), usually avoids branching
and can be read linearly.

Q: What are application security best practices?

A: Don't eval foreign code, manage user access to data, don't concatenate strings with foreign inputs to execute SQL
queries etc.

Q: How would you resolve DoS attack?

A: Out of the box firewalls, black listing IPs, control exposure of critical APIs with Access-Control-Allow-Origin.

Q: What code would you comment?

A: Code that is too complicated to be apparent on _what_ , or document the decision made on _how_ it does what it does.

Q: Have you developed any macros? If so, for what purpose?

A: Developed macros for DSL toy projects, macros for utilities (example cond-comp which composes functions
conditionally) or macros to improve performance by delegating computation to compile-time.

Q: If you application is slow, how would you approach it?

A: First thing one should check is if he's using the most efficient algorithms/data structures especially in
data-mangling intensive applications. Next would be to reduce IO cycles between services/applications, add caching
and/or memoization. And only then I would look at code tricks that improve performance, like transducers for minimizing
the intermediate lazy-sequences, faster data structure operations using their concrete Java API etc. A good source for
this is [Clojure Faster](https://tech.redplanetlabs.com/2020/09/02/clojure-faster/)
and [Fast and Elegant Clojure](https://bsless.github.io/fast-and-elegant-clojure/)

Q: What are the best practices for config management?

A: Configuration templates, that can be parameterized either through ENV variables or some other process in the CI/CD
pipeline

Q: What are main issues of apps that run in around-the-world datacenters?

A: Network latency, and state synchronization.

Q: How would you replace Factory design pattern in functional programming?

A: Using a higher-order function, that takes the parameters would pass to a Factory object and returns a function that when call construct the desired object.

Q: What is Scrum, Sprint, Planning, Product Owner & Scrum Master?

A: Scrum is a framework for project management. Sprint is a time-period (usually of fixed length for example 2 weeks) where in the beginning of it planning is done on what must be delivered until the end of the Sprint to provide maximum product value. Scrum Master is the person that is responsible for making sure that the Scrum framework and other agile principles are properly applied by the rest of the team. Product Owner is the person who coordinates between the stakeholders of a project and the development team and is responsible on optimizing the value output of the product.

Q: How agile retrospective looks like?

A: At the end of a sprint/epic you might do a retrospective on how the sprint/epic went. For example looking at whether task estimations were on point, or whether the correct tasks were picked from the backlog to the sprint for the optimized delivery of value.

Q: How would you build a team methodology?

A: A methodology that will be followed by a team must be decided by the team. Distribute questionnaire to find out what their preferences are on different processes and methodologies.

Q: A customer is pushing you to a code that's going to be unmaintainable. How would you resolve it?

A: Would try to talk with said customer to clarify why a feature could be unmaintainable. If no resolution is reached I would escalate it to the product owner or project manager.

Q: You see a colleague has huge productivity issues, how would you resolve it?

A: Take some time pair programming with him to examine where he might be most unproductive. It might be that he's not comfortable with his dev environment, or maybe has a hard time extracting a specification from the customer's requirements. In any case i would then point him to articles/conf talks that I've found very helpful in my experience.

Q: You find a security bug in your app. How would you proceed?

A: Check whether any of the libraries I use have any known security bugs. If not I'd check my codebase to see if there are any obvious vulnerability bad practices. If the problem is more complex I'd have to debug the specific situation and figure out what's wrong.

Q: You find a security bug in library you use. How would you proceed?

A: See if the security bug is related to a specific version, if so might consider upgrading/downgrading. If it's a specific interaction with the library that causes the vulnerability, I'd check the codebase to find whether the library is used in that unsafe way. In extreme cases, I would just cut the library and search for another that covers the same needs.

Q: What are techniques you always wanted to try and never had a chance to?

A: Logic programming using core.logic or other similar tools (i find logical queries using datalog to be very powerful). Category Theory in Clojure

Q: How many lines of code had systems you worked on in previous projects?
A: Usually the projects I've worked on may reach a few thousand lines of code. Biggest project I've worked on was a ~400k loc Java application.

Q: Can you quickly describe architecture of the previous system you've worked on?
A: At Juxt I've mostly worked in simple SPA client + REST server projects with a couple of independent services. Before that I've worked on a big Java project with hundreds of Microservices using Kafka.

Q: If you wanted to help a younger and less experienced engineer to get better as fast as possible, how would you lead them?
A: Share to them the resources that helped me learn, and offer mentorship where he can ask for guidance at anytime. Best way to learn is through exercises so I would give him simple exercises to implement on his own

Q: Can you provide reference to your previous companies?
A: Yes