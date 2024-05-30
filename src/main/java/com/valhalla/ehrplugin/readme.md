1. For every separate feature, create separate package according to the base
structure.
2. Try to code in such a way so that each separate packages can be exported 
in a different project.
3. For composite domain type, create subdomain packages under the main domain
in the same package and also create sub-controller packages under the
main controller in same package. Do the same with for service and repository
as well.
4. only use domains in the service classes and repository interfaces. other 
than that use dto. and from controller to service map domain and dto. 
5. For common utilities that could be used across features use the utility 
package. 
6. Implement swagger documentation.
