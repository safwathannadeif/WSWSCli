/**
 * http://www.bennadel.com/blog/2441-nested-views-routing-and-deep-linking-with-angularjs.htm
 * Correction for 1.2:
 * http://www.bennadel.com/blog/2560-compound-transclusion-prevented-in-angularjs-1-2.htm
 * 
 * 
 *     <!-- Render the ngInclude based on the switch. -->
    <div ng-switch="subview">
        <div ng-switch-when="one" ng-include=" 'one.htm' "></div>
        <div ng-switch-when="two" ng-include=" 'two.htm' "></div>
    </div>

 *     <script type="text/ng-template" id="two.htm">
 
        <div>
            Template Two
        </div>
 
    </script>

 */