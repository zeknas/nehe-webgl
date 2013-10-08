(ns nehe-webgl.core)

(def baby-scene (atom {}))

(defn set-in-scene [k v]
  (swap! baby-scene assoc (keyword k) v))

(defn start-all []
  (do
    (set-in-scene "canvas" (.getElementById js/document "canvas"))
    (set-in-scene "engine" (BABYLON.Engine. (:canvas @baby-scene) true))
    (set-in-scene "scene" (BABYLON.Scene. (:engine @baby-scene)))
    (set-in-scene "camera" (BABYLON.FreeCamera. "Camera" (BABYLON.Vector3. 0 0 -10) (:scene @baby-scene)))
    (set-in-scene "light" (BABYLON.PointLight. "Omni0" (BABYLON.Vector3. 0 100 100) (:scene @baby-scene)))
    (set-in-scene "triangle" (BABYLON.Mesh. "triangle" (:scene @baby-scene)))
    (.activeCamera.attachControl (:scene @baby-scene) (:canvas @baby-scene))))


(defn draw-triangle []
  (let [positions (clj->js [0 1 0 -1 -1 0 1 -1 0])
        normals (clj->js (take 9 (repeat 1)))
        indices (clj->js (range 3))]
    (doto (:triangle @baby-scene)
      (.setVerticesData positions BABYLON.VertexBuffer.PositionKind)
      (.setVerticesData normals BABYLON.VertexBuffer.NormalKind)
      (.setIndices indices))))

(defn move-to [obj x y z]
  (set! (.-position ((keyword obj) @baby-scene)) (BABYLON.Vector3. x y z)))

(defn draw-stuff []
  (do
    (set! (.-clearColor (:scene @baby-scene)) (BABYLON.Color3. 0 0 0))
    (draw-triangle)
    (move-to "triangle" -1.5 3 0)))

(defn ^:export init []
  (do
    (start-all)
    (draw-stuff)
    (.runRenderLoop (:engine @baby-scene) (fn [] (.render (:scene @baby-scene))))))
